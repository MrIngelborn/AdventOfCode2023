fun main() {
    val inputParser = InputParser("input.txt")
    val hasher = Hasher(inputParser.lines)
    hasher.hashSum.let(::println)
}