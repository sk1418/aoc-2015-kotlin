import utils.*

// https://adventofcode.com/2015/day/22
fun main() {
    val today = "Day22"

    val input = readInput(today)
    val testInput = readTestInput(today)

    fun playGame(playerHpAutoReduce: Int = 0): Int {
        minimumMana = Int.MAX_VALUE
        val initialState = GameState(
            playerHp = 50,
            playerMana = 500,
            playerArmor = 0,
            bossHp = 55,
            bossDamage = 8,
            manaSpent = 0
        )
        simulate(initialState, playerTurn = true, playerHpAutoReduce)
        return minimumMana
    }

    fun part1(input: List<String>) = playGame()

    fun part2(input: List<String>) = playGame(1)

//    chkTestInput(Part1, testInput, 0L) { part1(it) }
    solve(Part1, input) { part1(it) }

//    chkTestInput(Part2, testInput, 0L) { part2(it) }
    solve(Part2, input) { part2(it) }
}

private var minimumMana = Int.MAX_VALUE

private data class GameState(
    val playerHp: Int,
    val playerMana: Int,
    val playerArmor: Int,
    val bossHp: Int,
    val bossDamage: Int,
    val manaSpent: Int,
    val shieldTimer: Int = 0,
    val poisonTimer: Int = 0,
    val rechargeTimer: Int = 0
)

private val spells = mapOf(
    "Magic Missile" to 53,
    "Drain" to 73,
    "Shield" to 113,
    "Poison" to 173,
    "Recharge" to 229
)


private fun applyEffects(state: GameState): GameState {
    val (playerHp, playerMana, playerArmor, bossHp, bossDamage, manaSpent, shieldTimer, poisonTimer, rechargeTimer) = state
    val newArmor = if (shieldTimer > 0) 7 else 0
    val newBossHp = bossHp - if (poisonTimer > 0) 3 else 0
    val newPlayerMana = playerMana + if (rechargeTimer > 0) 101 else 0
    return state.copy(
        playerArmor = newArmor,
        bossHp = newBossHp,
        playerMana = newPlayerMana,
        shieldTimer = if (shieldTimer > 0) shieldTimer - 1 else 0,
        poisonTimer = if (poisonTimer > 0) poisonTimer - 1 else 0,
        rechargeTimer = if (rechargeTimer > 0) rechargeTimer - 1 else 0
    )
}

private fun simulate(state: GameState, playerTurn: Boolean, playerHpAutoReduce: Int) {
    var currentState = state

    if (playerTurn) {
        // Player loses x HP at the start of their turn
        currentState = currentState.copy(playerHp = currentState.playerHp - playerHpAutoReduce)
        if (currentState.playerHp <= 0) return // Player loses
    }

    // Apply effects
    currentState = applyEffects(currentState)

    // Check win/lose conditions
    if (currentState.bossHp <= 0) {
        minimumMana = kotlin.math.min(minimumMana, currentState.manaSpent)
        return
    }
    if (currentState.playerHp <= 0 || currentState.manaSpent >= minimumMana) return

    if (playerTurn) {
        // Player's turn: Try all spells
        for ((spell, cost) in spells) {
            if (cost > currentState.playerMana) continue // Can't afford the spell
            if ((spell == "Shield" && currentState.shieldTimer > 0) ||
                (spell == "Poison" && currentState.poisonTimer > 0) ||
                (spell == "Recharge" && currentState.rechargeTimer > 0)
            ) continue // Effect already active

            // Cast the spell
            val nextState = when (spell) {
                "Magic Missile" -> currentState.copy(
                    playerMana = currentState.playerMana - cost,
                    bossHp = currentState.bossHp - 4,
                    manaSpent = currentState.manaSpent + cost
                )

                "Drain" -> currentState.copy(
                    playerMana = currentState.playerMana - cost,
                    playerHp = currentState.playerHp + 2,
                    bossHp = currentState.bossHp - 2,
                    manaSpent = currentState.manaSpent + cost
                )

                "Shield" -> currentState.copy(
                    playerMana = currentState.playerMana - cost,
                    manaSpent = currentState.manaSpent + cost,
                    shieldTimer = 6
                )

                "Poison" -> currentState.copy(
                    playerMana = currentState.playerMana - cost,
                    manaSpent = currentState.manaSpent + cost,
                    poisonTimer = 6
                )

                "Recharge" -> currentState.copy(
                    playerMana = currentState.playerMana - cost,
                    manaSpent = currentState.manaSpent + cost,
                    rechargeTimer = 5
                )

                else -> error("Unknown spell: $spell")
            }

            simulate(nextState, playerTurn = false, playerHpAutoReduce)
        }
    } else { // Boss's turn: Boss attacks
        val damage = kotlin.math.max(1, currentState.bossDamage - currentState.playerArmor)
        simulate(currentState.copy(playerHp = currentState.playerHp - damage), playerTurn = true, playerHpAutoReduce)
    }
}