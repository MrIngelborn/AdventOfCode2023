import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import java.io.File
import org.junit.jupiter.api.Test

class ScratchcardsTest {
    private val testFile = "test_input.txt"
    private lateinit var cardCollection: CardCollection
    private val cards: List<Card>
        get() = cardCollection.cards
    private val firstCardWinningNumbers = setOf(41, 48, 83, 86, 17)
    private val firstCardNumbersYouHave = setOf(83, 86, 6, 31, 17, 9, 48, 53)

    private fun getTestDataLines() = object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

    @BeforeEach
    fun createCards() {
        cardCollection = CardCollection.createFromLines(getTestDataLines())
    }

    @Test
    fun canReadAllCards() {
        Assertions.assertEquals(6, cards.size)
    }

    @Test
    fun canParseWinningNumbers() {
        Assertions.assertEquals(firstCardWinningNumbers, cards[0].winningNumbers)
    }

    @Test
    fun canParseNumbersYouHave() {
        Assertions.assertEquals(firstCardNumbersYouHave, cards[0].numbersYouHave)
    }

    @Test
    fun canGetWinningCards() {
        Assertions.assertEquals(setOf(48, 83, 17, 86), cards[0].getWinningNumbersYouHave())
        Assertions.assertEquals(setOf(32, 61), cards[1].getWinningNumbersYouHave())
    }

    @Test
    fun canCalculatePointsForCard() {
        Assertions.assertEquals(8, cards[0].getPoints())
        Assertions.assertEquals(2, cards[1].getPoints())
        Assertions.assertEquals(0, cards[5].getPoints())
    }

    @Test
    fun sumOfAllPoints() {
        Assertions.assertEquals(13, cards.map(Card::getPoints).sum())
    }

    @Test
    fun canAddInstances() {
        cardCollection.playGame()
        Assertions.assertEquals(1, cards[0].instances)
        Assertions.assertEquals(2, cards[1].instances)
        Assertions.assertEquals(14, cards[4].instances)
        Assertions.assertEquals(1, cards[5].instances)
    }

    @Test
    fun instancesSumIsCorrect() {
        cardCollection.playGame()
        Assertions.assertEquals(30, cardCollection.instancesSum)
    }

}