class InitSequence(val steps: List<Step>) {

    companion object Factory {
        fun parseLines(lines: List<String>): InitSequence {
            val strings = lines.joinToString("").split(',')
            val steps = strings.map(Step::create)
            return InitSequence(steps)
        }
    }

    val hashSum: Int
        get() = steps.map(Step::toString).map(Hasher::hash).sum()

}
