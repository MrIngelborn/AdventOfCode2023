package cube_conundrum

import java.io.File
import java.net.URL

const val fileName = "input.txt"

const val redLimit = 12
const val greenLimit = 13
const val blueLimit = 14
private fun getResource(fileName: String): URL? = object {}.javaClass.getResource(fileName)
fun main() {
    val games = getResource(fileName)
            ?.let(URL::toURI)
            ?.let(::File)
            ?.readLines()
            ?.asSequence()
            ?.map(Game::create)
            ?.toList() !!

    val possibleGames = games.filter{ game -> game.isPossible(redLimit, greenLimit, blueLimit) }

    val gameIdSum = possibleGames.map(Game::gameId).sum()

    println(gameIdSum)
}