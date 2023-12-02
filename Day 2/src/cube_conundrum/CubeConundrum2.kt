package cube_conundrum

import java.io.File
import java.net.URL
fun main() {
    val games = getGamesList()

    val powerSum = games.map(Game::lowestAmountPossible)
            .sumOf { rgbTriple -> rgbTriple.first * rgbTriple.second * rgbTriple.third }

    println(powerSum)
}