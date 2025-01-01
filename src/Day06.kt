import utils.*

// https://adventofcode.com/2015/day/6
fun main() {
    val today = "Day06"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun toMatrix() = buildMap {
        (0..999).forEach { x -> (0..999).forEach { y -> put(x to y, 0) } }
    }.toMutableMap().toMutableNotNullMap().let { MatrixDay06(it) }

    fun part1(input: List<String>): Int = toMatrix().apply { controlSwitch(input) }.countOn()

    fun part2(input: List<String>): Int = toMatrix().apply { controlBrightness(input) }.sumBrightness()

    chkTestInput(Part1, testInput, 1000 * 1000) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 1000 * 1000) { part2(it) }
    solve(Part2, input) { part2(it) }
}

private class MatrixDay06(override val points: MutableNotNullMap<Pair<Int, Int>, Int>) : Matrix<Int>(999, 999, points) {
    private val posRe = """\d+,\d+""".toRegex()
    fun controlSwitch(lines: List<String>) {
        lines.forEach { line ->
            val (from, to) = posRe.findAll(line).map { it.value.split(",").let { it[0].toInt() to it[1].toInt() } }.toList()
            val op = if ("on" in line) {
                { _: Int -> 1 }
            } else if ("off" in line) {
                { _: Int -> 0 }
            } else {
                { b: Int -> if (b == 0) 1 else 0 }
            }
            (from.first..to.first).forEach { x ->
                (from.second..to.second).forEach { y ->
                    points[x to y] = op(points[x to y])
                }
            }
        }
    }

    fun countOn() = points.values.count { it == 1 }

    //part2
    fun controlBrightness(lines: List<String>) {
        lines.forEach { line ->
            val (from, to) = posRe.findAll(line).map { it.value.split(",").let { it[0].toInt() to it[1].toInt() } }.toList()
            val op = if ("on" in line) {
                { i: Int -> i + 1  }
            } else if ("off" in line) {
                { i: Int -> if (i - 1 < 0) 0 else i - 1 }
            } else {
                { i: Int -> i + 2 }
            }
            (from.first..to.first).forEach { x ->
                (from.second..to.second).forEach { y ->
                    points[x to y] = op(points[x to y])
                }
            }
        }
    }

    fun sumBrightness() = points.values.sumOf { it }
}