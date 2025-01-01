// https://adventofcode.com/2015/day/5
fun main() {
    val today = "Day05"

    val input = readInput(today)
    val testInput = readTestInput(today)
    val part2TestInput = readTestInput("$today-part2")


    fun part1(input: List<String>): Int = input.count { it.isNice() }
    fun part2(input: List<String>): Int = input.count { it.isNice2() }

    chkTestInput(Part1, testInput, 2) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, part2TestInput, 2) { part2(it) }
    solve(Part2, input) { part2(it) }
}

private val withoutRe = "ab|cd|pq|xy".toRegex()
private val doubleLetterRe = "([a-z])\\1".toRegex()
private val vowelsRe = "[aeiou]".toRegex()

private fun String.isNice() = (withoutRe !in this) && (doubleLetterRe in this) && split(vowelsRe).size > 3

private val aPairOf2Re = "([a-z][a-z]).*\\1".toRegex()
private val inBetweenRe = "([a-z])[a-z]\\1".toRegex()
private fun String.isNice2() = aPairOf2Re in this && inBetweenRe in this