import utils.*

// https://adventofcode.com/2015/day/11
fun main() {
    val today = "Day11"

    /**
     * solved by hand
     *The difficult requirements are
     * - must have two sets of double letters (aa, bb, etc)
     * - must have three consecutive ascending letters (abc, bcd, etc)
     *
     * The shortest way to meet these requirements is with a string of the form "aabcc"
     *
     * As we are looking for the *next* password, we will only change characters at the end of the string, and we will
     * change as few as possible.
     *
     * So, assuming that our last password does not have any double letters, ascending characters or forbidden
     * characters early in the string, we're looking for the next string of the form "xxx11233" - i.e. the starting letters
     * remain the same and we end up with an "aabcc" pattern at the end.
     *
     * To find the next possible password, we avoid changing the fifth from last letter if at all possible.
     *
     * My input is hxbxwxba - x is the fifth from last letter
     *
     * Therefore, the first four characters can stay the same and the next password is hxbxxyzz
     *
     * For the password after this, I must increment the fifth from last character. Neither y or z can start an aabcc string
     * so we wrap around to a. The next password is hxcaabcc
     */

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun part1(input: List<String>): Long {
        return 0
    }

    fun part2(input: List<String>): Long {
        return 0
    }

    chkTestInput(Part1, testInput, 0L) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}