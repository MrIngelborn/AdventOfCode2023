fun main() {
    val inputParser = InputParser("input.txt")
    val initSequence = InitSequence.parseLines(inputParser.lines)
    initSequence.hashSum.let(::println)

    val boxes = Boxes()
    boxes.applySteps(initSequence.steps)
    boxes.focusPower.let(::println)
}