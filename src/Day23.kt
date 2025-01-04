import utils.*

// https://adventofcode.com/2015/day/23
fun main() {
    val today = "Day23"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>) = Day23(input).let { it.execute();it.result["b"] }

    fun part2(input: List<String>) = Day23(input, 1).let { it.execute();it.result["b"] }

//    chkTestInput(Part1, testInput, 0L) { part1(it) }
    solve(Part1, input) { part1(it) }

//    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}

private data class Day23(val input: List<String>, val initalA: Int = 0) {
    val result = mutableMapOf("a" to initalA, "b" to 0).toMutableNotNullMap()
    fun execute() {
        var idx = 0
        while (idx <= input.lastIndex) {
            input[idx].split(" |, ".toRegex()).let { sp ->
                when (sp[0]) {
                    //@formatter:off
                    "hlf" -> {result[sp[1]] /=2; idx++}
                    "tpl" -> {result[sp[1]] *=3; idx++}
                    "inc" -> {result[sp[1]] +=1; idx++}
                    "jmp" -> {idx+=sp[1].toInt()}
                    "jie" -> { if (result[sp[1]] % 2 == 0) idx += sp[2].toInt() else idx++ }
                    "jio" -> { if (result[sp[1]]  == 1) idx += sp[2].toInt() else idx++ }
                    else -> error("Invalid input")
                    //@formatter:on
                }
            }
        }
    }


}