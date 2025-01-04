import utils.*

// https://adventofcode.com/2015/day/20
fun main() {
    val today = "Day20"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>): Int {
        val threshold = input.single().toInt()
        val houses = MutableList(threshold / 10 + 1) { 0 }
        val elfs = List(threshold / 10 + 1) { it }.drop(1)
        elfs.forEach {
            var seq = it
            while (seq <= houses.lastIndex) {
                houses[seq] += (10 * it)
                seq += it
            }
        }
        return houses.indexOfFirst { it > threshold }
    }

    fun part2(input: List<String>): Int {
        val threshold = input.single().toInt()
        val houses = MutableList(threshold / 10 + 1) { 0 }
        val elfs = List(threshold / 10 + 1) { it }.drop(1)
        elfs.forEach {
            var seq = it
            var times = 0
            while (seq <= houses.lastIndex && times <= 50) {
                houses[seq] += (11 * it)
                seq += it
                times++
            }
        }
        return houses.indexOfFirst { it > threshold }
    }

    chkTestInput(Part1, testInput, 6) { part1(it) }
    solve(Part1, input) { part1(it) }

//    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}