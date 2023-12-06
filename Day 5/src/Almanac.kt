class Almanac(val seeds: List<Int>) {
    companion object Factory {
        fun create(testDataLines: List<String>): Almanac {
            return Almanac(listOf())
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
