import utils.*

// https://adventofcode.com/2015/day/18
fun main() {
    val today = "Day18"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun toMatrix(input: List<String>) = buildMap {
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c -> put(x to y, c) }
        }
    }.toMutableNotNullMap().let { MatrixDay18(input.first().lastIndex, input.lastIndex, it) }

    fun part1(input: List<String>, steps: Int): Int {
        val matrix = toMatrix(input)
        repeat(steps) { matrix.aSecondPassed() }
        return matrix.points.values.count { it == '#' }
    }

    fun part2(input: List<String>, steps: Int): Int {
        val matrix = toMatrix(input)
        repeat(steps) { matrix.aSecondPassed(true) }
        return matrix.points.values.count { it == '#' }
    }

    chkTestInput(Part1, testInput, 4) { part1(it, 4) }
    solve(Part1, input) { part1(it, 100) }

    chkTestInput(Part2, testInput, 17) { part2(it, 5) }
    solve(Part2, input) { part2(it, 100) }
}

private class MatrixDay18(maxX: Int, maxY: Int, override val points: MutableNotNullMap<Pair<Int, Int>, Char>) : Matrix<Char>(maxX, maxY, points) {
    private fun lightOn4Corner() = listOf(0 to 0, 0 to maxY, maxX to 0, maxX to maxY).forEach { points[it] = '#' }
    fun aSecondPassed(cornerAlwaysOn: Boolean = false) {
        if (cornerAlwaysOn) lightOn4Corner()
        buildMap {
            (0..maxX).forEach { x ->
                (0..maxY).forEach { y ->
                    val pos = Pair(x, y)
                    val onCount = pos.allAround().count { points[it] == '#' }

                    when (onCount) {
                        3 -> put(pos, true)
                        2 -> put(pos, points[pos] == '#')
                        else -> put(pos, false)
                    }
                }

            }
        }.forEach { (x, on) -> points[x] = if (on) '#' else '.' }
        if (cornerAlwaysOn) lightOn4Corner()
    }

}