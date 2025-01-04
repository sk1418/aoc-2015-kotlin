import utils.*

// https://adventofcode.com/2015/day/14
fun main() {
    val today = "Day14"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val re = """^(\S+).*?(\d+).*?(\d+).*?(\d+)""".toRegex()
    fun parseInput(input: List<String>): List<Player> {
        return input.map {
            re.find(it).let { m ->
                Player(m!!.groups[1]!!.value, m.groups[2]!!.value.toInt(), m.groups[3]!!.value.toInt(), m.groups[4]!!.value.toInt())
            }
        }
    }

    fun part1(input: List<String>, totalSeconds: Int): Int = parseInput(input).maxOf { p -> p.flyAfter(totalSeconds) }

    fun part2(input: List<String>, totalSeconds: Int): Int {
        val players = parseInput(input)
        (1..totalSeconds).forEach { s -> players.groupBy { it.flyAfter(s) }.toSortedMap().lastEntry().value.forEach { it.points++ } }
        return players.maxOf { it.points }
    }

    chkTestInput(Part1, testInput, 1120) { part1(it, 1000) }
    solve(Part1, input) { part1(it, 2503) }

    chkTestInput(Part2, testInput, 689) { part2(it, 1000) }
    solve(Part2, input) { part2(it, 2503) }
}

private data class Player(val name: String, val speed: Int, val canLast: Int, val rest: Int) {
    var points: Int = 0
    fun flyAfter(total: Int): Int {
        val cycle = total / (canLast + rest)
        val finalFlySecond = (total % (canLast + rest)).let { r -> if (r >= canLast) canLast else r }
        return cycle * canLast * speed + speed * finalFlySecond
    }
}