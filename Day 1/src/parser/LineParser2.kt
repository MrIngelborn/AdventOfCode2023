package parser

import java.io.File
import java.net.URL

class LineParser2 {
    private val multiDigitPattern = Regex("(one|two|three|four|five|six|seven|eight|nine|\\d).*(one|two|three|four|five|six|seven|eight|nine|\\d).*(?!one|two|three|four|five|six|seven|eight|nine|\\d)\$")
    private val singleDigitPattern = Regex("(one|two|three|four|five|six|seven|eight|nine|\\d)")

    fun parseFile(fileName: String): Int =
            getResource(fileName)
                    ?.let(URL::toURI)
                    ?.let(::File)
                    ?.readLines()
                    ?.asSequence()
                    ?.map(this::findMatchingPattern)
                    ?.map { result -> result?.groups }
                    ?.map { groups -> extractGroupValues(groups)}
                    ?.map { digitPair -> convertDigitText(digitPair.first) to convertDigitText(digitPair.second) }
                    ?.map { digitPair -> concatDigits(digitPair)}
                    ?.sumOf(Integer::parseInt)
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

    private fun convertDigitText(text: String?): String? =
        when (text) {
            "one" -> "1"
            "two" -> "2"
            "three" -> "3"
            "four" -> "4"
            "five" -> "5"
            "six" -> "6"
            "seven" -> "7"
            "eight" -> "8"
            "nine" -> "9"
            else -> text
        }

}

fun main() {
    println(LineParser2().parseFile("input.txt"))
}