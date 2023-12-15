import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LensLibraryTest {

    private lateinit var initSequence: InitSequence

    @BeforeEach
    fun createSequence() {
        val inputParser = InputParser("test_input.txt")
        initSequence = InitSequence.parseLines(inputParser.lines)
    }

    @Test
    fun canParseData() {
        val steps: List<Step> = initSequence.steps
        assertEquals(11, steps.size)

        val strings: List<String> = steps.map { step -> step.toString() }
        assertEquals("rn=1", strings[0])
        assertEquals("cm-", strings[1])
        assertEquals("ot=7", strings[10])
    }

    @Test
    fun canHashString() {
        val hashValue: Int = Hasher.hash("HASH")
        assertEquals(52, hashValue)
    }

    @Test
    fun canHashStep() {
        assertEquals(30, initSequence.steps[0].hashValue)
    }

    @Test
    fun canCalculateHashSum() {
        assertEquals(1320, initSequence.hashSum)
    }


}