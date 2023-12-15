fun main() {
    val inputParser = InputParser("input.txt")
    val hasher = Hasher.parseLines(inputParser.lines)
    hasher.hashSum.let(::println)
}