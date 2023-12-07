class Hand(val cards: List<Card>) : Comparable<Hand> {
    companion object Factory {
        fun create(string: String) = Hand(string.map(::Card))
    }
    override fun compareTo(other: Hand): Int {
        TODO("Not yet implemented")
    }
}