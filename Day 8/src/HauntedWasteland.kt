import java.io.File


private const val inputFileName = "input.txt"

private val inputLines
    get() = object {}.javaClass.getResource(inputFileName)?.toURI()!!.let(::File).readLines()

fun main() {
    val network = Network.create(inputLines)
    network.getSteps().let(::println)
}