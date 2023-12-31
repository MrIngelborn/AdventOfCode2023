import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class CamelCardsTest {
    private val testFile = "test_input.txt"
    private val testDataLines
        get() = object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

    private lateinit var camelCards: CamelCards

    @BeforeEach
    fun createTimeSheet() {
        camelCards = CamelCards.create(testDataLines)
    }

    @Test
    fun canParseCardsInFirstHand() {
        val handToBidPars: List<Pair<Hand, Int>> = camelCards.handToBidPairs
        val firstHand = handToBidPars[0].first
        val firstHandCards: List<Card> = firstHand.cards
        assertEquals('3', firstHandCards[0].value)
        assertEquals('2', firstHandCards[1].value)
        assertEquals('T', firstHandCards[2].value)
        assertEquals('3', firstHandCards[3].value)
        assertEquals('K', firstHandCards[4].value)
    }

    @Test
    fun hasParsedAllLines() {
        assertEquals(5, camelCards.handToBidPairs.size)
    }

    @Test
    fun canParseBids() {
        val bidList = camelCards.handToBidPairs.map { pair -> pair.second }
        assertEquals(listOf(765, 684, 28, 220, 483), bidList)
    }

     @Test
     fun canOrderCards() {
         assertTrue(Card('3') > Card('2'))
         assertTrue(Card('4') == Card('4'))
         assertTrue(Card('T') > Card('9'))
         assertTrue(Card('K') > Card('Q'))
         assertTrue(Card('J') < Card('2'))
     }

    @Test
    fun canOrderHands() {
        assertTrue(Hand.create("32T3K") < Hand.create("KK677"))
        assertTrue(Hand.create("KK677") < Hand.create("T55J5"))
        assertTrue(Hand.create("T55J5") < Hand.create("QQQJA"))
        assertTrue(Hand.create("QQQJA") < Hand.create("KTJJT"))
    }

    @Test
    fun canSortHandBetPairs() {
        val sortedPairs: List<Pair<Hand,Int>> = camelCards.sortedHandBidPairs
        assertEquals(listOf(
            camelCards.handToBidPairs[0],
            camelCards.handToBidPairs[2],
            camelCards.handToBidPairs[1],
            camelCards.handToBidPairs[4],
            camelCards.handToBidPairs[3],
        ), sortedPairs)
    }

    @Test
    fun canCalculateTotalWinnings() {
        val sortedPairs: List<Pair<Hand,Int>> = camelCards.sortedHandBidPairs
        assertEquals(5905, camelCards.totalWinnings)
    }
}
