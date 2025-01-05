import utils.Part1
import utils.readInput
import utils.solve

// https://adventofcode.com/2015/day/25
fun main() {
    val today = "Day25"

    val input = readInput(today)
    fun findNumberIndex(row: Int, col: Int) =
        (1..<row).fold(initial = 1) { acc, n -> acc + n }.let { idx ->
            (1..<col).fold(initial = idx) { acc, n -> acc + n + row }
        }

    // input row:2947 col: 3029
    fun part1(): Long {
        val idx = findNumberIndex(2947, 3029)
        return (1..<idx).fold(initial = 20151125) { acc, _ -> 252533 * acc % 33554393 }
    }
    solve(Part1, input) { part1() }

}