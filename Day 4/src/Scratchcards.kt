import java.io.File

private const val inputFileName = "input.txt"

fun main() {
    val cardCollection = CardCollection.createFromLines(getInputLines())

    cardCollection.cards.map(Card::getPoints).sum().let(::println)
}

private fun getInputLines() =
    object {}.javaClass.getResource(inputFileName)?.toURI()!!.let(::File).readLines()