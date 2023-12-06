import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import java.io.File
import org.junit.jupiter.api.Test

class AlmanacTest {
    private val testFile = "test_input.txt"

    private fun getTestDataLines() = object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

    private lateinit var almanac: Almanac

    @BeforeEach
    fun createAlmanac() {
        almanac = Almanac.create(getTestDataLines())
    }

    @Test
    fun canParseSeeds() {
        val seedSet: Set<Long> = almanac.seeds.toSet()
        Assertions.assertEquals(setOf(79L, 14L, 55L, 13L), seedSet)
    }

    @Test
    fun canMapSeedToSoil() {
        Assertions.assertEquals(81, almanac.mapSeedToSoil(79))
        Assertions.assertEquals(14, almanac.mapSeedToSoil(14))
        Assertions.assertEquals(57, almanac.mapSeedToSoil(55))
        Assertions.assertEquals(13, almanac.mapSeedToSoil(13))
    }

    @Test
    fun canMapSoilToFertilizer() {
        Assertions.assertEquals(81, almanac.mapSoilToFertilizer(81))
        Assertions.assertEquals(53, almanac.mapSoilToFertilizer(14))
        Assertions.assertEquals(57, almanac.mapSoilToFertilizer(57))
        Assertions.assertEquals(52, almanac.mapSoilToFertilizer(13))
    }

    @Test
    fun canMapFertilizerToWater() {
        Assertions.assertEquals(81, almanac.mapFertilizerToWater(81))
        Assertions.assertEquals(49, almanac.mapFertilizerToWater(53))
        Assertions.assertEquals(53, almanac.mapFertilizerToWater(57))
        Assertions.assertEquals(41, almanac.mapFertilizerToWater(52))
    }

    @Test
    fun canMapWaterToLight() {
        Assertions.assertEquals(74, almanac.mapWaterToLight(81))
        Assertions.assertEquals(42, almanac.mapWaterToLight(49))
        Assertions.assertEquals(46, almanac.mapWaterToLight(53))
        Assertions.assertEquals(34, almanac.mapWaterToLight(41))
    }

    @Test
    fun canMapLightToTemperature() {
        Assertions.assertEquals(78, almanac.mapLightToTemperature(74))
        Assertions.assertEquals(42, almanac.mapLightToTemperature(42))
        Assertions.assertEquals(82, almanac.mapLightToTemperature(46))
        Assertions.assertEquals(34, almanac.mapLightToTemperature(34))
    }

    @Test
    fun canMapTemperatureToHumidity() {
        Assertions.assertEquals(78, almanac.mapTemperatureToHumidity(78))
        Assertions.assertEquals(43, almanac.mapTemperatureToHumidity(42))
        Assertions.assertEquals(82, almanac.mapTemperatureToHumidity(82))
        Assertions.assertEquals(35, almanac.mapTemperatureToHumidity(34))
    }

    @Test
    fun canMapHumidityToLocation() {
        Assertions.assertEquals(82, almanac.mapHumidityToLocation(78))
        Assertions.assertEquals(43, almanac.mapHumidityToLocation(43))
        Assertions.assertEquals(86, almanac.mapHumidityToLocation(82))
        Assertions.assertEquals(35, almanac.mapHumidityToLocation(35))
    }

    @Test
    fun canFindLowestLocation() {
        Assertions.assertEquals(35, almanac.lowestLocation)
    }

}