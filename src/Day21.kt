import utils.*

// https://adventofcode.com/2015/day/21
fun main() {
    val today = "Day21"

    val input = readInput(today)
    val testInput = readTestInput(today)

    val _me = Player21(100, 0, 0)

    fun part1(input: List<String>): Int {
        val result = mutableListOf<Int>()
        weapons.forEach { w ->
            val me = _me.copy()
            me.arm(w)
            armors.forEach { a ->
                val meA = me.copy()
                meA.arm(a)
                armorRings.forEach { aRing ->
                    val meAR = meA.copy()
                    meAR.arm(aRing)
                    damageRings.forEach { dRing ->
                        val meDR = meAR.copy()
                        meDR.arm(dRing)
                        if (meDR.winTheBossFight()) {
                            result.add(meDR.cost)
                        }
                    }
                }
            }
        }

        return result.min()
    }

    fun part2(input: List<String>): Int {
        val result = mutableListOf<Int>()
        weapons.forEach { w ->
            val me = _me.copy()
            me.arm(w)
            armors.forEach { a ->
                val meA = me.copy()
                meA.arm(a)
                armorRings.forEach { aRing ->
                    val meAR = meA.copy()
                    meAR.arm(aRing)
                    damageRings.forEach { dRing ->
                        val meDR = meAR.copy()
                        meDR.arm(dRing)
                        if (!meDR.winTheBossFight()) {
                            result.add(meDR.cost)
                        }
                    }
                }
            }
        }

        return result.max()
    }

//    chkTestInput(Part1, testInput, 0L) { part1(it) }
    solve(Part1, input) { part1(it) }

//    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}

private val boss = Player21(103, 9, 2)

private data class Player21(var hit: Int, var damage: Int, var armor: Int, var cost: Int = 0) {

    private fun damageToBoss() = (damage - boss.armor).let { if (it <= 0) 1 else it }
    private fun damageToMe() = (boss.damage - armor).let { if (it <= 0) 1 else it }

    fun winTheBossFight(): Boolean {
        val b = boss.copy()
        while (hit > 0 && b.hit > 0) {
            b.hit -= damageToBoss()
            if (b.hit > 0) {
                hit -= damageToMe()
            }
        }
        return hit > 0

    }

    fun arm(w: Weapon) {
        damage += w.damage()
        cost += w.cost()
    }

    fun arm(a: Armor) {
        armor += a.armor()
        cost += a.cost()

    }

    fun arm(r: DamageRing) {
        damage += r.damage()
        cost += r.cost()
    }

    fun arm(r: ArmorRing) {
        armor += r.armor()
        cost += r.cost()
    }
}

@JvmInline
value class Weapon(val pair: Pair<Int, Int>) {
    fun cost() = pair.second
    fun damage() = pair.first
}

@JvmInline
value class Armor(val pair: Pair<Int, Int>) {
    fun cost() = pair.second
    fun armor() = pair.first
}

@JvmInline
value class DamageRing(val pair: Pair<Int, Int>) {
    fun cost() = pair.second
    fun damage() = pair.first
}

@JvmInline
value class ArmorRing(val pair: Pair<Int, Int>) {
    fun cost() = pair.second
    fun armor() = pair.first
}


// damage to cost
private val weapons = listOf(
    Weapon(4 to 8),
    Weapon(5 to 10),
    Weapon(6 to 25),
    Weapon(7 to 40),
    Weapon(8 to 74)
)

// armor to cost
private val armors = listOf(
    Armor(0 to 0),
    Armor(1 to 13),
    Armor(2 to 31),
    Armor(3 to 53),
    Armor(4 to 75),
    Armor(5 to 102)
)

// rings
private val armorRings = listOf(
    ArmorRing(0 to 0),
    ArmorRing(1 to 20),
    ArmorRing(2 to 40),
    ArmorRing(3 to 80)
)
private val damageRings = listOf(
    DamageRing(0 to 0),
    DamageRing(1 to 25),
    DamageRing(2 to 50),
    DamageRing(3 to 100)
)