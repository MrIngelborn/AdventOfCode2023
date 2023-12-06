class Almanac(
    val seeds: Set<Int>,
    private val seedToSoilMap: Map<Pair<Int, Int>, Int>,
    private val soilToFertilizerMap: Map<Pair<Int, Int>, Int>,
    private val fertilizerToWaterMap: Map<Pair<Int, Int>, Int>,
    private val waterToLightMap: Map<Pair<Int, Int>, Int>,
    private val lightToTemperatureMap: Map<Pair<Int, Int>, Int>,
    private val temperatureToHumidityMap: Map<Pair<Int, Int>, Int>,
    private val humidityToLocationMap: Map<Pair<Int, Int>, Int>
) {
    companion object Factory {
        private val numberRegex = Regex("\\d+")
        private val seedsRegex = Regex("seeds:(.*)")
        private val mapHeaderRegex = Regex("\\w+-to-\\w+ map:")
        private val mapLineRegex = Regex("(\\d+)\\s+(\\d+)\\s+(\\d+)")

        fun create(lines: List<String>): Almanac {
            val mutableLines = lines.toMutableList()
            val seeds: Set<Int> = parseSeeds(mutableLines)
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
                    val mapLineValues =
                        it.groups.toList().subList(1, 4).map { group -> group!!.value }.map(Integer::parseInt)
                    val destinationRangeStart = mapLineValues[0]
                    val sourceRangeStart = mapLineValues[1]
                    val rangeLength = mapLineValues[2]
                    map.put(sourceRangeStart to sourceRangeStart + rangeLength - 1, destinationRangeStart)
                }
            } while (lines.isNotEmpty() && lines.first.isNotBlank())
            return map
        }
    }

    val lowestLocation: Int
        get() = seeds.asSequence()
            .map(::mapSeedToSoil)
            .map(::mapSoilToFertilizer)
            .map(::mapFertilizerToWater)
            .map(::mapWaterToLight)
            .map(::mapLightToTemperature)
            .map(::mapTemperatureToHumidity)
            .map(::mapHumidityToLocation)
            .reduce(Math::min)

    private fun applyMap(map: Map<Pair<Int, Int>, Int>, i: Int): Int {
        map.forEach { entry: Map.Entry<Pair<Int, Int>, Int> ->
            if (i in entry.key.first..entry.key.second)
                return (i - entry.key.first) + entry.value
        }
        return i
    }

    fun mapSeedToSoil(seed: Int): Int = applyMap(seedToSoilMap, seed)

    fun mapSoilToFertilizer(soil: Int): Int = applyMap(soilToFertilizerMap, soil)

    fun mapFertilizerToWater(fertilizer: Int): Int = applyMap(fertilizerToWaterMap, fertilizer)

    fun mapWaterToLight(water: Int): Int = applyMap(waterToLightMap, water)

    fun mapLightToTemperature(light: Int): Int = applyMap(lightToTemperatureMap, light)

    fun mapTemperatureToHumidity(temperature: Int): Int = applyMap(temperatureToHumidityMap, temperature)

    fun mapHumidityToLocation(humidity: Int): Int = applyMap(humidityToLocationMap, humidity)

}
