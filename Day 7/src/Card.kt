class Card(val value: Char) : Comparable<Card> {
    override operator fun compareTo(other: Card): Int {
        return 0
    }
}