class Boxes {
    val boxMap: MutableMap<Int, Box> = mutableMapOf()

    operator fun get(index: Int): Box = boxMap.getOrPut(index, ::Box)

    fun applyStep(step: Step) {
        when (step.operation) {
            Operation.ADD -> this[step.hashValue].add(step)
            Operation.DELETE -> this[step.hashValue].remove(step)
        }
    }

    fun applySteps(steps: List<Step>) {
        steps.forEach(::applyStep)
    }

    val focusPower: Int
        get() = boxMap.map { (index, box) ->
            (index + 1) * box.focusPower
        }.sum()

}
