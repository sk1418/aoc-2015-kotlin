import utils.*

// https://adventofcode.com/2015/day/9
fun main() {
    val today = "Day09"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val re = """(\S+) to (\S+) = (\d+)""".toRegex()

    fun parseInput(input: List<String>): MutableNotNullMap<Set<String>, Int> {
        return buildMap {
            input.forEach {
                re.find(it).also { m ->
                    put(setOf(m!!.groups[1]!!.value, m.groups[2]!!.value), m.groups[3]!!.value.toInt())
                }
            }
        }.toMutableNotNullMap()
    }

    fun part1(input: List<String>): Int {
        val theMap = parseInput(input)
        return permutations(theMap.keys.flatten().distinct()).minOf{ route ->
            route.windowed(2).sumOf { pair -> theMap[pair.toSet()] }
        }
    }

    fun part2(input: List<String>): Int {
        val theMap = parseInput(input)
        return permutations(theMap.keys.flatten().distinct()).maxOf{ route ->
            route.windowed(2).sumOf { pair -> theMap[pair.toSet()] }
        }
    }

    chkTestInput(Part1, testInput, 605) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 982) { part2(it) }
    solve(Part2, input) { part2(it) }
}