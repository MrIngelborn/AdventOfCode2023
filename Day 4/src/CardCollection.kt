class CardCollection (val cards: List<Card>) {
    val instancesSum: Int
        get() = cards.map(Card::instances).sum()

    fun playGame() {
        TODO("Not yet implemented")
    }

    companion object Factory {
        fun createFromLines(lines: List<String>): CardCollection =
            lines.mapNotNull { line -> Card.create(line) }.let(::CardCollection)
    }

}