fun main() {
    val inputParser = InputParser("input.txt")
    val initSequence = InitSequence.parseLines(inputParser.lines)
    initSequence.hashSum.let(::println)
}