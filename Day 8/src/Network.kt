class Network(val instructions: Collection<Instruction>, val nodeMap: Map<String, Pair<String, String>>) {

    companion object Factory {
        fun create(testData1Lines: Any): Network {
            TODO()
        }
    }

    fun getInstruction(index: Int): Instruction {
        TODO()
    }

    fun getSteps(): Int {
        TODO()
    }

}
