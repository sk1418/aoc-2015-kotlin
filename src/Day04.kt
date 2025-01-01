import utils.*

// https://adventofcode.com/2015/day/4
fun main() {
    val today = "Day04"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val iSeq = generateSequence(1) { it + 1 }

    fun part1(input: List<String>): Int {
        val s = input.single()
        return iSeq.first {i-> "$s$i".md5().startsWith("00000") }
    }

    fun part2(input: List<String>): Int {
        val s = input.single()
        return iSeq.first {i-> "$s$i".md5().startsWith("000000") }
    }

    chkTestInput(Part1, testInput, 609043) { part1(it) }
    solve(Part1, input) { part1(it) }

    solve(Part2, input) { part2(it) }
}