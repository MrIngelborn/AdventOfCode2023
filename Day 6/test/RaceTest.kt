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
        assertEquals(setOf(7 to 9, 15 to 40, 30 to 200), timeSheet.timeDistancePairs)
    }

    @Test
    fun canCalculateNumberOfWaysToWin() {
        assertEquals(4, timeSheet.numberOfWaysToWin(7 to 9))
        assertEquals(8, timeSheet.numberOfWaysToWin(15 to 40))
        assertEquals(9, timeSheet.numberOfWaysToWin(30 to 200))
    }

    @Test
    fun canCalculateNumberOfWaysToWinProduct() {
        assertEquals(288, timeSheet.numberOfWaysToWinProduct)
    }

}