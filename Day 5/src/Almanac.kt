class Almanac(val seeds: Set<Int>) {
    companion object Factory {
        private val numberRegex = Regex("\\d+")
        private val seedsRegex = Regex("seeds:(.*)")

        fun create(lines: List<String>): Almanac {
            val mutableLines = lines.toMutableList()
            val seeds: Set<Int> = parseSeeds(mutableLines)

            return Almanac(seeds)
        }

        private fun parseSeeds(lines: MutableList<String>): Set<Int> {
            var seedSet : Set<Int>?
            do {
                seedSet = seedsRegex.find(lines.removeFirst())?.let { result ->
                    val seedsString = result.groups[1]!!.value
                    numberRegex.findAll(seedsString).map(MatchResult::value).map(Integer::parseInt).toSet()
                }
            } while (seedSet == null)
            return seedSet
        }
    }

    val lowestLocation: Int
        get() = 0

    fun mapSeedToSoil(i: Int): Int {
        return 0
    }

    fun mapSoilToFertilizer(i: Int): Int {
        return 0
    }

    fun mapFertilizerToWater(i: Int): Int {
        return 0
    }

    fun mapWaterToLight(i: Int): Int {
        return 0
    }

    fun mapLightToTemperature(i: Int): Int {
        return 0
    }

    fun mapTemperatureToHumidity(i: Int): Int {
        return 0
    }

    fun mapHumidityToLocation(i: Int): Int {
        return 0
    }

}
