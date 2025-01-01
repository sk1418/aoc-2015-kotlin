// https://adventofcode.com/2015/day/9
fun main() {
    val today = "Day09"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val re = """(\S+) to (\S+) = (\d+)""".toRegex()
    fun part1(input: List<String>): Int {
        val distances = mutableListOf<Int>()
        val places = mutableSetOf<String>()
        input.forEach {
            re.find(it).also { m ->
                places.add(m!!.groups[1]!!.value)
                places.add(m.groups[2]!!.value)
                distances.add(m.groups[3]!!.value.toInt())
            }
        }
        return distances.sorted().take(places.size-1).sum()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    chkTestInput(Part1, testInput, 605) { part1(it) }
    solve(Part1, input) { part1(it) }

    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}