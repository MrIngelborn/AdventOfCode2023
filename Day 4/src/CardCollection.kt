class CardCollection (val cards: List<Card>) {

    companion object Factory {
        fun createFromLines(lines: List<String>): CardCollection =
            lines.mapNotNull { line -> Card.create(line) }.let(::CardCollection)
    }

}