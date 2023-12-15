import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LensLibraryTest {

    private lateinit var hasher: Hasher

    @BeforeEach
    fun createHasher() {
        val inputParser = InputParser("test_input.txt")
        hasher = Hasher.parseLines(inputParser.lines)
    }

    @Test
    fun canParseData() {
        val strings: List<String> = hasher.strings
        assertEquals(11, strings.size)
        assertEquals("rn=1", strings[0])
        assertEquals("cm-", strings[1])
        assertEquals("ot=7", strings[10])
    }

    @Test
    fun canHashString() {
        val hashValue: Int = hasher.hash("HASH")
        assertEquals(52, hashValue)
    }

    @Test
    fun canCalculateHashSum() {
        assertEquals(1320, hasher.hashSum)
    }
}