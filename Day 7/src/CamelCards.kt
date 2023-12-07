class CamelCards(val handToBidPairs: List<Pair<Hand, Int>>) {

    companion object Factory {
        fun create(lines: List<String>): CamelCards {
            return CamelCards(listOf())
        }
    }

    val sortedHandBidPairs: List<Pair<Hand, Int>>
        get() {
            TODO()
        }


    val totalWinnings: Int
        get() {
            TODO()
        }

}


