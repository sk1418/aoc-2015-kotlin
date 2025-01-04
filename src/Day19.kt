import utils.*

// https://adventofcode.com/2015/day/19
fun main() {
    val today = "Day19"

    val input = readInput(today)
    val testInput = readTestInput(today)
    fun parseInput(input: List<String>): Pair<String, List<Pair<String, String>>> {
        val replacements = input.takeWhile { it.isNotBlank() }.map { it.split(" => ").let { it[0] to it[1] } }
        val line = input.last()
        return line to replacements
    }

    fun part1(input: List<String>): Int {
        val (txt, replacements) = parseInput(input)
        return replacements.map { (from, to) ->
            buildList {
                var scanIdx = 0
                while (true) {
                    val start = txt.indexOf(from, startIndex = scanIdx)
                    if (start < 0) break
                    txt.let { it.substring(0, start) + to + it.substring(start + from.length) }
                        .also { add(it) }
                    scanIdx = start + from.length
                }
            }
        }.flatten().distinct().size
    }

    fun part2(input: List<String>): Int {
        val (txt, replacements) = parseInput(input)
        var target = txt
        var times = 0
        while (target != "e") {
            replacements.forEach { (from, to) ->
                if (to in target) {
                    target = target.replaceFirst(to, from)
                    times++
                }
            }
        }

        return times
    }

    chkTestInput(Part1, testInput, 7) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 6) { part2(it) }
    solve(Part2, input) { part2(it) }
}