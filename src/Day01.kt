import utils.*

// https://adventofcode.com/2015/day/1
fun main() {
    val today = "Day01"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>): Int {
        val map = input.first().groupingBy { it }.eachCount().toNotNullMap()
        return map['('] - map[')']
    }

    fun part2(input: List<String>): Int {
        val ints = input.first().map { if (it == '(') 1 else -1 }
        var sum = 0
        for (i in ints.indices) {
            sum += ints[i]
            if (sum == -1) return i + 1

        }
        return -1
    }

    chkTestInput(Part1, testInput, -1) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 5) { part2(it) }
    solve(Part2, input) { part2(it) }
}