import gear_ratios.Number
import gear_ratios.Schematic
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.io.File

class GearRatiosTest {
    private val testFile = "test_input.txt"
    private val numberValuesInTestData = listOf(467, 114, 35, 633, 617, 58, 592, 755, 664, 598)
    private val numberValuesWithoutSymbol = listOf(114, 58)

    fun getTestData() =
            object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

    lateinit var schematic: Schematic

    @BeforeEach
    fun parseTestData() {
        schematic = Schematic.parseLines(getTestData())
    }

    private fun getNumbers(): Collection<Number> = schematic.numbers

    @Test
    fun canReadAllNumbers() {
        val numbers: Collection<Number> = getNumbers()
        val numberOfNumbers = numbers.size

        Assertions.assertEquals(10, numberOfNumbers)
    }

    @TestFactory
    fun numbersExists() {
        numberValuesInTestData.map { numberValue ->
            DynamicTest.dynamicTest("hasNumber: $numberValue") { numberExists(numberValue) }
        }
    }

    private fun numberExists(numberValue: Int) {
        val numbersValues: Collection<Int> = getNumbers().map(Number::value)
        Assertions.assertTrue(numbersValues.contains(numberValue))
    }
    @TestFactory
    fun numbersHasNoSymbol() {
        val numbersWithoutSymbol = getNumbers().filter { number: Number -> !number.hasSymbol() }
        val numbersValues: Collection<Int> = numbersWithoutSymbol.map(Number::value)

        numberValuesWithoutSymbol.map { numberValue ->
            DynamicTest.dynamicTest("numberHasNoSymbol: $numberValue") {
                numbersValues.contains(numberValue)
            }
        }
    }



}