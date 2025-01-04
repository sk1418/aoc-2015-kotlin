import utils.*

// https://adventofcode.com/2015/day/24
fun main() {
    val today = "Day24"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>): Long = Day24(input).solve(3)

    fun part2(input: List<String>): Long = Day24(input).solve(4)

    chkTestInput(Part1, testInput, 99) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 44) { part2(it) }
    solve(Part2, input) { part2(it) }
}

private data class Day24(val input: List<String>) {
    val nums = input.map { it.toInt() }
    fun solve(groupSize: Int): Long {
        val targetSum = nums.sum() / groupSize
        for (i in 2..nums.lastIndex - 2) {
            val list = nums.combinations(i).filter { it.sum() == targetSum }
            if (list.isNotEmpty()) {
                return list.minOf { it.fold(initial = 1L) { acc, next -> acc * next.toLong() } }
            }
        }
        return -1
    }
}