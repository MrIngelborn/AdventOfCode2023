class CamelCards(val handToBidPairs: List<Pair<Hand, Int>>) {

    companion object Factory {
        private val lineRegex = Regex("([\\dTJQKA]+)\\s+(\\d+)")
        fun create(lines: List<String>): CamelCards {
            val handToBidPairs = lines.map { line ->
                lineRegex.find(line)?.groupValues?.let { values ->
                    val hand: Hand = Hand.create(values[1])
                    val bet = values[2].toInt()
                    hand to bet
                }
            }

            return CamelCards(handToBidPairs.filterNotNull())
        }
    }

    val sortedHandBidPairs: List<Pair<Hand, Int>>
        get() = handToBidPairs.sortedBy { pair: Pair<Hand, Int> -> pair.first }

    val totalWinnings: Int
        get() {
            TODO()
        }

}


