import java.io.File

private const val inputFileName = "input.txt"

fun main() {
    val almanac = Almanac.create(getInputLines())
    almanac.lowestLocation.let(::println)
}
private fun getInputLines() =
    object {}.javaClass.getResource(inputFileName)?.toURI()!!.let(::File).readLines()