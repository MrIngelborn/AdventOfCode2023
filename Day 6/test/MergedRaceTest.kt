import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class MergedRaceTest {
    private val testFile = "test_input.txt"

    private fun getTestDataLines() = object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

    private lateinit var timeSheet: TimeSheet

    @BeforeEach
    fun createTimeSheet() {
        timeSheet = TimeSheet.createMerged(getTestDataLines())
    }

    @Test
    fun canParseMergedNumber() {
        assertEquals(setOf(71530 to 940200), timeSheet.timeDistancePairs)
    }

    @Test
    fun canCalculateNumberOfWays() {
        assertEquals(71503, timeSheet.numberOfWaysToWinProduct)
    }


}