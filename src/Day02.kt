import utils.*

// https://adventofcode.com/2015/day/2
fun main() {
    val today = "Day02"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun parseInput(input: List<String>) = input.map { it.split("x").let { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) } }
    fun part1(input: List<String>): Int = parseInput(input).sumOf { (a, b, c) ->
        listOf(a * b, a * c, b * c).let { l -> l.sumOf { 2 * it } + l.min() }
    }

    fun part2(input: List<String>): Int = parseInput(input).sumOf { (a, b, c) ->
        listOf(a, b, c).let { l ->
            (l - l.max()).sum() * 2 + l.fold(1) { acc, next -> acc * next }
        }
    }

    chkTestInput(Part1, testInput, 58 + 43) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 34 + 14) { part2(it) }
    solve(Part2, input) { part2(it) }
}