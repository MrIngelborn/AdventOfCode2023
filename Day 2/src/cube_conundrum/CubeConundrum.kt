package cube_conundrum

import java.io.File
import java.net.URL

const val fileName = "input.txt"

private const val redLimit = 12
private const val greenLimit = 13
private const val blueLimit = 14
fun getResource(fileName: String): URL? = object {}.javaClass.getResource(fileName)
fun main() {
    val games = getGamesList()

    val possibleGames = games.filter{ game -> game.isPossible(redLimit, greenLimit, blueLimit) }

    val gameIdSum = possibleGames.map(Game::gameId).sum()

    println(gameIdSum)
}

fun getGamesList(): List<Game> = getResource(fileName)
            ?.let(URL::toURI)
            ?.let(::File)
            ?.readLines()
            ?.asSequence()
            ?.map(Game::create)
            ?.toList()!!