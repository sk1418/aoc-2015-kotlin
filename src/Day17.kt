import utils.*

// https://adventofcode.com/2015/day/17
fun main() {
    val today = "Day17"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>, totalLiter: Int): Int {
        val boxes = input.map { it.toInt() }
        return (1..boxes.size).flatMap { boxes.combinations(it) }.filter { it.sum() == totalLiter }.size
    }

    fun part2(input: List<String>, totalLiter: Int): Int {
        val boxes = input.map { it.toInt() }
        return (1..boxes.size).flatMap { boxes.combinations(it) }.filter { it.sum() == totalLiter }
            .groupBy { it.size }.minByOrNull { it.key }!!.value.size
    }

    chkTestInput(Part1, testInput, 4) { part1(it, 25) }
    solve(Part1, input) { part1(it, 150) }

    chkTestInput(Part2, testInput, 3) { part2(it, 25) }
    solve(Part2, input) { part2(it, 150) }
}