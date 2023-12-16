class Step(val operation: Operation, val label: String, val focalLength: Int?) {

    companion object Factory {
        private val addOperationRegex = Regex("(\\w+)=(\\d)")
        private val deleteOperationRegex = Regex("(\\w+)-")
        fun create(string: String): Step = when {
            addOperationRegex.matches(string) -> {
                val groupValues = addOperationRegex.find(string)!!.groupValues
                Step(Operation.ADD, groupValues[1], groupValues[2].toInt())
            }

            deleteOperationRegex.matches(string) -> {
                val groupValues = deleteOperationRegex.find(string)!!.groupValues
                Step(Operation.DELETE, groupValues[1], null)
            }

            else -> throw IllegalArgumentException("Could not parse string to Step")
        }
    }


    val hashValue: Int
        get() = Hasher.hash(this.label)

    override fun toString(): String {
        return label + when (operation) {
            Operation.ADD -> "=$focalLength"
            Operation.DELETE -> "-"
        }
    }
}
