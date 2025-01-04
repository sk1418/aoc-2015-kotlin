import utils.*

// https://adventofcode.com/2015/day/16
fun main() {
    val today = "Day16"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val dict = buildMap {
        put("children", 3)
        put("cats", 7)
        put("samoyeds", 2)
        put("pomeranians", 3)
        put("akitas", 0)
        put("vizslas", 0)
        put("goldfish", 5)
        put("trees", 3)
        put("cars", 2)
        put("perfumes", 1)
    }.toNotNullMap()

    fun parseInput(input: List<String>): List<Map<String, Int>> {
        //Sue xx: akitas: 5, goldfish: 6, vizslas: 6
        return input.map {
            it.replace("^.*?: ".toRegex(), "").replace(", ", ": ")
                .split(": ").let { l ->
                    buildMap { l.chunked(2).forEach { put(it[0], it[1].toInt()) } }
                }
        }
    }

    fun check(m: Map<String, Int>) = m.entries.all { (k, v) ->
        when (k) {
            "cats", "trees" -> v > dict[k]
            "pomeranians", "goldfish" -> v < dict[k]
            else -> v == dict[k]
        }
    }

    fun part1(input: List<String>)= parseInput(input).indexOfFirst { m -> m.entries.all { (k, v) -> v == dict[k] } } + 1

    fun part2(input: List<String>) = parseInput(input).indexOfFirst { m -> check(m) } + 1

//    chkTestInput(Part1, testInput, 0) { part1(it) }
    solve(Part1, input) { part1(it) }

//    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}