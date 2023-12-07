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
            val jokers = cardGroups.filter { list -> list.contains(Card('J')) }
            val cardGroupsNoJokers = cardGroups.filterNot { list -> list.contains(Card('J')) }

            val numberOfJokers = jokers.flatten().size
            val maxSize = (cardGroupsNoJokers.maxOfOrNull(List<Card>::size) ?: 0) + numberOfJokers

            return when (cardGroupsNoJokers.size) {
                0,1 -> Type.FIVE
                2 -> {
                    if (maxSize == 4) return Type.FOUR
                    return Type.HOUSE
                }

                3 -> {
                    if (maxSize == 3) return Type.THREE
                    return Type.TWO_PAIR
                }

                4 -> Type.PAIR
                5 -> Type.HIGH
                else -> throw IllegalStateException()
            }
        }

    override fun toString(): String {
        return cards.joinToString("", transform = Card::toString)
    }
}