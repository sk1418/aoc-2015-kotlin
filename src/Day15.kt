import utils.*
import kotlin.math.max

// https://adventofcode.com/2015/day/15
fun main() {
    val today = "Day15"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val re = """-?\d+""".toRegex()
    fun parseInput(input: List<String>) = input.map { line -> re.findAll(line).map { it.value.toInt() }.toList() }

    fun part1(input: List<String>): Int {
        val theList = parseInput(input)
        var max = 0
        for (i in 0..100) {
            for (j in 0..100 - i) {
                for (k in 0..100 - i - j) {
                    val h = 100 - i - j - k
                    val a = theList[0][0] * i + theList[1][0] * j + theList[2][0] * k + theList[3][0] * h
                    val b = theList[0][1] * i + theList[1][1] * j + theList[2][1] * k + theList[3][1] * h
                    val c = theList[0][2] * i + theList[1][2] * j + theList[2][2] * k + theList[3][2] * h
                    val d = theList[0][3] * i + theList[1][3] * j + theList[2][3] * k + theList[3][3] * h
                    val e = theList[0][4] * i + theList[1][4] * j + theList[2][4] * k + theList[3][4] * h

                    if (listOf(a, b, c, d, e, e).all { it > 0 }) {
                        max = max(a * b * c * d, max)
                    }
                }
            }
        }

        return max
    }

    fun part2(input: List<String>): Int {
        val theList = parseInput(input)
        var max = 0
        for (i in 0..100) {
            for (j in 0..100 - i) {
                for (k in 0..100 - i - j) {
                    val h = 100 - i - j - k
                    val a = theList[0][0] * i + theList[1][0] * j + theList[2][0] * k + theList[3][0] * h
                    val b = theList[0][1] * i + theList[1][1] * j + theList[2][1] * k + theList[3][1] * h
                    val c = theList[0][2] * i + theList[1][2] * j + theList[2][2] * k + theList[3][2] * h
                    val d = theList[0][3] * i + theList[1][3] * j + theList[2][3] * k + theList[3][3] * h
                    val e = theList[0][4] * i + theList[1][4] * j + theList[2][4] * k + theList[3][4] * h

                    if (e == 500) {
                        if (listOf(a, b, c, d, e, e).all { it > 0 }) {
                            max = max(a * b * c * d, max)
                        }
                    }
                }
            }
        }

        return max
    }

//        chkTestInput(Part1, testInput, 0L) { part1(it) }
    solve(Part1, input) { part1(it) }
//        chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}