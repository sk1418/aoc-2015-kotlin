import utils.*
import utils.Direction.*

// https://adventofcode.com/2015/day/3
fun main() {
    val today = "Day03"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>): Int {
        var cur = 0 to 0
        return buildSet {
            add(cur)
            input.single().forEach { c ->
                cur = cur.move(c.toDirection())
                add(cur)
            }
        }.size
    }

    fun part2(input: List<String>): Int {
        var santa = 0 to 0
        var robot = 0 to 0
        return buildSet {
            add(0 to 0)
            input.single().forEachIndexed { idx, c ->
                if (idx % 2 == 0) {
                    santa = santa.move(c.toDirection())
                    add(santa)
                } else {
                    robot = robot.move(c.toDirection())
                    add(robot)
                }
            }
        }.size
    }

    chkTestInput(Part1, testInput, 4) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 3) { part2(it) }
    solve(Part2, input) { part2(it) }
}

private fun Char.toDirection() = when (this) {
    '^' -> Up
    '>' -> Right
    'v' -> Down
    '<' -> Left
    else -> error("Invalid input")
}

private fun Pair<Int, Int>.move(direction: Direction) = when (direction) {
    Up -> first to (second - 1)
    Down -> first to (second + 1)
    Left -> (first - 1) to second
    Right -> (first + 1) to second
}