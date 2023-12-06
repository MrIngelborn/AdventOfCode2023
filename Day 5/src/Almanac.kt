class Almanac(val seeds: Set<Int>,
              private val seedToSoilMap: Map<Pair<Int, Int>, Int>) {
    companion object Factory {
        private val numberRegex = Regex("\\d+")
        private val seedsRegex = Regex("seeds:(.*)")
        private val mapHeaderRegex = Regex("\\w+-to-\\w+ map:")
        private val mapLineRegex = Regex("(\\d+)\\s+(\\d+)\\s+(\\d+)")

        fun create(lines: List<String>): Almanac {
            val mutableLines = lines.toMutableList()
            val seeds: Set<Int> = parseSeeds(mutableLines)
            val seedToSoilMap = parseMap(mutableLines)

            return Almanac(seeds, seedToSoilMap)
        }

        private fun parseSeeds(lines: MutableList<String>): Set<Int> {
            var seedSet: Set<Int>?
            do {
                seedSet = seedsRegex.find(lines.removeFirst())?.let { result ->
                    val seedsString = result.groups[1]!!.value
                    numberRegex.findAll(seedsString).map(MatchResult::value).map(Integer::parseInt).toSet()
                }
            } while (seedSet == null)
            return seedSet
        }

        private fun parseMap(lines: MutableList<String>): Map<Pair<Int, Int>, Int> {
            while (!mapHeaderRegex.matches(lines.removeFirst()));

            val map = mutableMapOf<Pair<Int, Int>, Int>()
            do {
                mapLineRegex.find(lines.removeFirst())?.let {
                    val mapLineValues = it.groups.toList().subList(1,4).map { group -> group!!.value }.map(Integer::parseInt)
                    val destinationRangeStart = mapLineValues[0]
                    val sourceRangeStart = mapLineValues[1]
                    val rangeLength = mapLineValues[2]
                    map.put(sourceRangeStart to sourceRangeStart + rangeLength-1, destinationRangeStart)
                }
            } while (lines.first.isNotBlank())
            return map
        }
    }

    val lowestLocation: Int
        get() = 0

    fun mapSeedToSoil(seed: Int): Int {
        seedToSoilMap.forEach { entry: Map.Entry<Pair<Int, Int>, Int> ->
            if (seed in entry.key.first..entry.key.second)
                return (seed-entry.key.first) + entry.value
        }
        return seed
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
