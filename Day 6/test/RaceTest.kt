import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class RaceTest {

    private val testFile = "test_input.txt"

    private fun getTestDataLines() = object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

    private lateinit var timeSheet: TimeSheet

    @BeforeEach
    fun createTimeSheet() {
        timeSheet = TimeSheet.create(getTestDataLines())
    }

    @Test
    fun canParseTimeDistance() {
        assertEquals(setOf(7L to 9L, 15L to 40L, 30L to 200L), timeSheet.timeDistancePairs)
    }

    @Test
    fun canCalculateNumberOfWaysToWin() {
        assertEquals(4, timeSheet.numberOfWaysToWin(7L to 9L))
        assertEquals(8, timeSheet.numberOfWaysToWin(15L to 40L))
        assertEquals(9, timeSheet.numberOfWaysToWin(30L to 200L))
    }

    @Test
    fun canCalculateNumberOfWaysToWinProduct() {
        assertEquals(288, timeSheet.numberOfWaysToWinProduct)
    }

}