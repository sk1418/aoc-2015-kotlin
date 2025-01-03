import com.google.gson.*
import utils.*

// https://adventofcode.com/2015/day/12
fun main() {
    val today = "Day12"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val numberRe = """-?\d+""".toRegex()
    fun part1(input: List<String>) = numberRe.findAll(input.single()).map { it.value.toInt() }.sum()

    fun part2(input: List<String>): Int {

        val element = JsonParser.parseString(input.single())
        return element.sumNumbers()
    }

    chkTestInput(Part1, testInput, 6) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 4) { part2(it) }
    solve(Part2, input) { part2(it) }

}

private fun JsonElement.sumNumbers(): Int {
    return when (this) {
        is JsonPrimitive -> {
            if (isNumber) asInt else 0
        }

        is JsonObject -> {
            asMap().values.let {
                if (it.any { j -> j.isJsonPrimitive && j.asString == "red" }) 0
                else it.sumOf { it.sumNumbers() }
            }
        }

        is JsonArray -> { sumOf { it.sumNumbers() } }
        else -> 0
    }

}