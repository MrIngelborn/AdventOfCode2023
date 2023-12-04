import kotlin.math.pow

class Card(val winningNumbers: Set<Int>, val numbersYouHave: Set<Int>) {
    companion object Factory {
        private val lineRegex = Regex("Card\\s+\\d+:(.*)\\|(.*)")
        private val numberRegex = Regex("\\d+")
        fun create(line: String): Card? =
            lineRegex.find(line)?.let { match ->
                val winningNumbers = match.groups[1]!!.value.let(::getIntSetFromString)
                val numbersYouHave = match.groups[2]!!.value.let(::getIntSetFromString)
                return Card(winningNumbers, numbersYouHave)
            }

        private fun getIntSetFromString(string: String): Set<Int> =
            numberRegex.findAll(string).map { match ->
                match.value.let(Integer::parseInt)
            }.toSet()
    }

    fun getWinningNumbersYouHave(): Set<Int> {
        return numbersYouHave.intersect(winningNumbers)
    }

    fun getPoints(): Int =
        getWinningNumbersYouHave()
            .takeUnless(Set<Int>::isEmpty)
            ?.count()
            ?.let { count ->
                2.0.pow(count-1).toInt()
            } ?: 0

}