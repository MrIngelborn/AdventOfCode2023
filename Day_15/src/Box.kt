class Box {
    private val labelToLens: LinkedHashMap<String, Int> = linkedMapOf()

    val focusPower: Int
        get() = labelToLens.sequencedValues().foldIndexed(0) { index, acc, focalValue ->
            acc + (index+1) * focalValue
        }
    operator fun get(i: Int): Int =
        labelToLens.sequencedValues().toList()[i]

    fun add(lens: Step) {
        labelToLens[lens.label] = lens.focalLength!!
    }

    fun remove(step: Step) {
        labelToLens.remove(step.label)
    }

    fun size(): Int =
        labelToLens.size


}
