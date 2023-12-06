class Almanac(
    val seeds: Set<Long>,
    private val seedToSoilMap: Map<Pair<Long, Long>, Long>,
    private val soilToFertilizerMap: Map<Pair<Long, Long>, Long>,
    private val fertilizerToWaterMap: Map<Pair<Long, Long>, Long>,
    private val waterToLightMap: Map<Pair<Long, Long>, Long>,
    private val lightToTemperatureMap: Map<Pair<Long, Long>, Long>,
    private val temperatureToHumidityMap: Map<Pair<Long, Long>, Long>,
    private val humidityToLocationMap: Map<Pair<Long, Long>, Long>
) {
    companion object Factory {
        private val numberRegex = Regex("\\d+")
        private val seedsRegex = Regex("seeds:(.*)")
        private val mapHeaderRegex = Regex("\\w+-to-\\w+ map:")
        private val mapLineRegex = Regex("(\\d+)\\s+(\\d+)\\s+(\\d+)")

        fun create(lines: List<String>): Almanac {
            val mutableLines = lines.toMutableList()
            val seeds: Set<Long> = parseSeeds(mutableLines)
            val seedToSoilMap = parseMap(mutableLines)
            val soilToFertilizerMap = parseMap(mutableLines)
            val fertilizerToWaterMap = parseMap(mutableLines)
            val waterToLightMap = parseMap(mutableLines)
            val lightToTemperatureMap = parseMap(mutableLines)
            val temperatureToHumidityMap = parseMap(mutableLines)
            val humidityToLocationMap = parseMap(mutableLines)

            return Almanac(
                seeds, seedToSoilMap, soilToFertilizerMap, fertilizerToWaterMap,
                waterToLightMap, lightToTemperatureMap, temperatureToHumidityMap, humidityToLocationMap
            )
        }

        private fun parseSeeds(lines: MutableList<String>): Set<Long> {
            var seedSet: Set<Long>?
            do {
                seedSet = seedsRegex.find(lines.removeFirst())?.let { result ->
                    val seedsString = result.groups[1]!!.value
                    numberRegex.findAll(seedsString).map(MatchResult::value).map(String::toLong).toSet()
                }
            } while (seedSet == null)
            return seedSet
        }

        private fun parseMap(lines: MutableList<String>): Map<Pair<Long, Long>, Long> {
            while (!mapHeaderRegex.matches(lines.removeFirst()));

            val map = mutableMapOf<Pair<Long, Long>, Long>()
            do {
                mapLineRegex.find(lines.removeFirst())?.let {
                    val mapLineValues =
                        it.groups.toList().subList(1, 4).map { group -> group!!.value }.map(String::toLong)
                    val destinationRangeStart = mapLineValues[0]
                    val sourceRangeStart = mapLineValues[1]
                    val rangeLength = mapLineValues[2]
                    map.put(sourceRangeStart to sourceRangeStart + rangeLength - 1, destinationRangeStart)
                }
            } while (lines.isNotEmpty() && lines.first.isNotBlank())
            return map
        }
    }

    val lowestLocation: Long
        get() = seeds.asSequence()
            .map(::mapSeedToSoil)
            .map(::mapSoilToFertilizer)
            .map(::mapFertilizerToWater)
            .map(::mapWaterToLight)
            .map(::mapLightToTemperature)
            .map(::mapTemperatureToHumidity)
            .map(::mapHumidityToLocation)
            .reduce(Math::min)

    private fun applyMap(map: Map<Pair<Long, Long>, Long>, i: Long): Long {
        map.forEach { entry: Map.Entry<Pair<Long, Long>, Long> ->
            if (i in entry.key.first..entry.key.second)
                return (i - entry.key.first) + entry.value
        }
        return i
    }

    fun mapSeedToSoil(seed: Long): Long = applyMap(seedToSoilMap, seed)

    fun mapSoilToFertilizer(soil: Long): Long = applyMap(soilToFertilizerMap, soil)

    fun mapFertilizerToWater(fertilizer: Long): Long = applyMap(fertilizerToWaterMap, fertilizer)

    fun mapWaterToLight(water: Long): Long = applyMap(waterToLightMap, water)

    fun mapLightToTemperature(light: Long): Long = applyMap(lightToTemperatureMap, light)

    fun mapTemperatureToHumidity(temperature: Long): Long = applyMap(temperatureToHumidityMap, temperature)

    fun mapHumidityToLocation(humidity: Long): Long = applyMap(humidityToLocationMap, humidity)

}
