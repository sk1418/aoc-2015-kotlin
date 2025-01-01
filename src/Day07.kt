import GateOp.*
import utils.Part1
import utils.Part2
import utils.readInput
import utils.solve

// https://adventofcode.com/2015/day/7
fun main() {
    val today = "Day07"

    val input = readInput(today)

    fun part1(input: List<String>): Int = Day07Solution(input).aValue() //46065
    fun part2(input: List<String>): Int = Day07Solution(input.filterNot { it.endsWith(" -> b") } + "46065 -> b").aValue()

    solve(Part1, input) { part1(it) }
    solve(Part2, input) { part2(it) }
}

private enum class GateOp { AND, OR, LSHIFT, RSHIFT, NOT }
private class Day07Solution(val input: List<String>) {


    private val varMap = mutableMapOf<String, Int>()
    private fun getOrCalc(name: String): Int = varMap.getOrPut(name) { operationMap.getValue(name).invoke() }
    private fun calc(a: String, b: String, op: GateOp): Int = (getOrCalc(a) to getOrCalc(b)).let { (v1, v2) ->
        when (op) {
            AND -> v1 and v2
            OR -> v1 or v2
            LSHIFT -> v1 shl v2
            RSHIFT -> v1 shr v2
            NOT -> v1.inv()
        }
    }

    private val operationMap: Map<String, () -> Int> = buildMap {
        input.forEach {
            it.split(" -> ").also { (oStr, vName) ->
                if (" " !in oStr) { //number or var
                    if (oStr.all { it.isDigit() })
                        varMap[vName] = oStr.toInt()
                    else
                        put(vName) { calc(oStr, oStr, AND) }

                } else {
                    val split = oStr.split(" ")
                    if (split.size == 3) {
                        split.also { (a, op, b) ->
                            if ("SHIFT" in op) { //xx SHIFT \d+
                                varMap[b] = b.toInt()
                            }
                            put(vName) { calc(a, b, GateOp.valueOf(op)) }
                        }
                    } else { // NOT
                        split.also { (op, a) ->
                            put(vName) { calc(a, a, NOT) }
                        }
                    }
                }
            }
        }
    }


    fun aValue() = getOrCalc("a")
}