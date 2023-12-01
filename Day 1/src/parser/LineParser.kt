package parser

import java.io.File
import java.net.URL

class LineParser {
    private val multiDigitPattern = Regex("(\\d).*(\\d).*(?!\\d)\$")
    private val singleDigitPattern = Regex("(\\d)")

    fun parseFile(fileName: String): Int =
            getResource(fileName)
                    ?.let(URL::toURI)
                    ?.let(::File)
                    ?.readLines()
                    ?.asSequence()
                    ?.map(this::findMatchingPattern)
                    ?.map { result -> result?.groups }
                    ?.map { groups -> extractGroupValues(groups)}
                    ?.map { digitPair -> concatDigits(digitPair)}
                    ?.sumOf {Integer.parseInt(it) }
                    ?: 0

    private fun getResource(fileName: String): URL? = object {}.javaClass.getResource(fileName)

    private fun findMatchingPattern(line: String): MatchResult? =
            multiDigitPattern.find(line) ?: singleDigitPattern.find(line)
    private fun concatDigits(digitPair: Pair<String?, String?>) = digitPair.first + digitPair.second

    private fun extractGroupValues(groups: MatchGroupCollection?): Pair<String?, String?> {
        val firstDigit = groups?.get(1)?.value
        val secondDigit = groups?.takeIf { group -> group.size > 2 }?.get(2)?.value ?: firstDigit
        return firstDigit to secondDigit
    }

}

fun main() {
    println(LineParser().parseFile("input.txt"))
}