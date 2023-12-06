class TimeSheet {

    val timeDistancePairs: Set<Pair<Int,Int>>
        get() = setOf()
    companion object Factory {
        fun create(lines: List<String>): TimeSheet {
            return TimeSheet()
        }
    }
    fun numberOfWaysToWin(pair: Pair<Int, Int>): Int {
        return 0
    }
}