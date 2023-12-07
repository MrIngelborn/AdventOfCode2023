import java.util.Objects

class Hand(val cards: List<Card>) : Comparable<Hand> {

    private enum class Type {
        FIVE, FOUR, HOUSE, THREE, TWO_PAIR, PAIR, HIGH
    }

    companion object Factory {
        fun create(string: String) = Hand(string.map(::Card))
    }

    override fun compareTo(other: Hand): Int {
        val typeComparison = type.compareTo(other.type)
        if (typeComparison != 0) {
            return -typeComparison
        }
        return cards.zip(other.cards) { a, b ->
            a.compareTo(b)
        }.filterNot { it == 0 }.first()
    }

    private val type: Type
        get() {
        val cardGroups = cards.groupBy { card -> card.value }.values
        val maxSize = cardGroups.maxOfOrNull(List<Card>::size)!!

        return when (cardGroups.size) {
            1 -> Type.FIVE
            2 -> {
                if (maxSize == 4) Type.FOUR
                Type.HOUSE
            }

            3 -> {
                if (maxSize == 3) Type.THREE
                Type.TWO_PAIR
            }

            4 -> Type.PAIR
            5 -> Type.HIGH
            else -> throw IllegalStateException()
        }
    }
}