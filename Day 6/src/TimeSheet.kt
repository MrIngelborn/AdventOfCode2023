import kotlin.math.roundToInt
import kotlin.math.sqrt

class TimeSheet(val timeDistancePairs: Set<Pair<Long, Long>>) {


    companion object Factory {
        private val lineRegex = Regex("\\w+:(.*)")
        private val numberRegex = Regex("\\d+")
        fun create(lines: List<String>): TimeSheet {
            val timeList: List<Long> = parseNumbers(lines[0])
            val distanceList: List<Long> = parseNumbers(lines[1])

            return TimeSheet(timeList.zip(distanceList).toSet())
        }

        private fun parseNumbers(line: String): List<Long> =
            lineRegex.find(line)!!.groups[1]!!.value
                .let { numberRegex.findAll(it) }
                .map(MatchResult::value)
                .map(String::toLong)
                .toList()

        fun createMerged(lines: List<String>): TimeSheet {
            val time = parseMergedNumber(lines[0])
            val distance = parseMergedNumber(lines[1])

            return TimeSheet(setOf(time to distance))
        }

        private fun parseMergedNumber(line: String): Long =
            lineRegex.find(line)!!.groups[1]!!.value
                .let { numberRegex.findAll(it) }
                .map(MatchResult::value)
                .joinToString("")
                .let(String::toLong)
    }

    fun numberOfWaysToWin(pair: Pair<Long, Long>): Long {
        val roots = solveForRealRoots(-1.0, pair.first.toDouble(), -pair.second.toDouble())!!.let(::roundPairToInt)
        return roots.second - roots.first + 1
    }

    private fun solveForRealRoots(a: Double, b: Double, c: Double): Pair<Double, Double>? {
        val discriminant = b * b - 4 * a * c
        if (discriminant < 0) {
            return null
        }
        val root1 = (-b + sqrt(discriminant)) / (2 * a)
        val root2 = (-b - sqrt(discriminant)) / (2 * a)
        return Pair(root1, root2)
    }

    private fun roundPairToInt(pair: Pair<Double, Double>): Pair<Long, Long> =
        pair.first.inc().toLong() to pair.second.minus(0.0001).toLong()

    val numberOfWaysToWinProduct: Long
        get() = timeDistancePairs.map(::numberOfWaysToWin).reduce(Long::times)
}