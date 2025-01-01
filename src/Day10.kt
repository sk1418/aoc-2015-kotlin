import utils.*

// https://adventofcode.com/2015/day/10
fun main() {
    val today = "Day10"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>, times: Int): Int {
        var s = input.single()
        repeat(times) {
            s.groupContinuousChars().let { segList ->
                s = buildString {
                    segList.forEach { seg -> append(seg.let { "${it.size}${it.first()}" }) }
                }
            }
        }
        return s.length
    }

    fun part2(input: List<String>) = part1(input, 50)

    chkTestInput(Part1, testInput, 6) { part1(it, 5) }
    solve(Part1, input) { part1(it, 40) }

//    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}