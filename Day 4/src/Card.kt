class Card(val winningNumbers: Set<Int>, val numbersYouHave: Set<Int>) {
    companion object Factory {
        fun create(line: String): Card {
            return Card(setOf(),setOf())
        }
    }

    fun getWinningNumbersYouHave(): Set<Int> {
        return setOf()
    }

    fun getPoints(): Int = 0

}