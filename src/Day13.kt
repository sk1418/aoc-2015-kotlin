import utils.*

// https://adventofcode.com/2015/day/13
fun main() {
    val today = "Day13"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun parseInput(input: List<String>): MutableNotNullMap<Set<String>, Int> {
        return buildMap {
            input.forEach {
                it.replace(" would gain", "")
                    .replace(" would lose ", " -")
                    .replace(" happiness.*next to ".toRegex(), " ").dropLast(1)
                    .split(" ")
                    .let { (a, v, b) ->
                        val key = setOf(a, b)
                        put(key, getOrElse(key) { 0 } + v.toInt())
                    }
            }
        }.toMutableNotNullMap()
    }

    fun part1(input: List<String>): Int {
        val dict = parseInput(input)
        val guys = dict.keys.flatten().distinct()
        return permutations(guys).maxOf { pList ->
            pList.windowed(2).sumOf { two -> dict[two.toSet()] } + dict[setOf(pList.first(), pList.last())]
        }
    }

    fun part2(input: List<String>): Int {
        val dict = parseInput(input)
        val allGuys = dict.keys.flatten().distinct().onEach { dict[setOf("me", it)]=0 } + "me"
        return permutations(allGuys).map { pList ->
            pList.windowed(2).sumOf { two -> dict[two.toSet()] } + dict[setOf(pList.first(), pList.last())]
        }.max()
    }

    chkTestInput(Part1, testInput, 330) { part1(it) }
    solve(Part1, input) { part1(it) }

//    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}