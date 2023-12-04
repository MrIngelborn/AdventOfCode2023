import kotlin.math.max
import kotlin.math.min

class CardCollection (val cards: List<Card>) {
    val instancesSum: Int
        get() = cards.map(Card::instances).sum()

    fun playGame() {
        cards.forEachIndexed { cardIndex, card ->
            val matchingNumbers = card.getWinningNumbersYouHave().size
            for (i in cardIndex+1..min(cardIndex+matchingNumbers, cards.size-1)) {
                cards[i].instances += card.instances
            }
        }
    }

    companion object Factory {
        fun createFromLines(lines: List<String>): CardCollection =
            lines.mapNotNull { line -> Card.create(line) }.let(::CardCollection)
    }

}