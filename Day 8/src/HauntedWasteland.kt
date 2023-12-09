import java.io.File
import java.math.BigInteger


private const val inputFileName = "input.txt"

private val inputLines
    get() = object {}.javaClass.getResource(inputFileName)?.toURI()!!.let(::File).readLines()

fun main() {
    val network = Network.create(inputLines)
    network.getSteps().let(::println)
    network.startingNodes.let(::println)
    network.getStepsAllStartingNodes().let(::println)
}