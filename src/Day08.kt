// https://adventofcode.com/2015/day/8
fun main() {
    val today = "Day08"

    val input = readInput(today)
    val testInput = readTestInput(today)
    fun part1(input: List<String>) = input.sumOf { str ->
        str.length - str.trim('"')
            .replace("""\"""", "A")
            .replace("""\\""", "B")
            .replace("""\\x[0-9a-f]{2}""".toRegex(), "C")
            .length
    }

    fun part2(input: List<String>)=input.sumOf { str ->
        str.replace("""\""","AA")
            .replace(""""""", "BB")
            .length - str.length + 2
    }

    chkTestInput(Part1, testInput, 12) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 19) { part2(it) }
    solve(Part2, input) { part2(it) }

}