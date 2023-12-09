import java.util.Collections
import java.util.stream.Collectors

class Network(val instructions: List<Instruction>, val nodeMap: Map<String, Pair<String, String>>) {


    companion object Factory {
        private val instructionsRegex = Regex("[RL]+")
        private val nodeRegex = Regex("(\\w+) = \\((\\w+), (\\w+)\\)")
        fun create(lines: List<String>): Network {
            val instructions = parseInstructions(lines[0])!!
            val nodeMap = parseNodes(lines.subList(2, lines.size))

            return Network(instructions, nodeMap)
        }

        private fun parseInstructions(line: String) =
            instructionsRegex.find(line)?.value?.fold(mutableListOf<Instruction>()) { list, char ->
                list.add(when (char) {
                    'R' -> Instruction.RIGHT
                    'L' -> Instruction.LEFT
                    else -> throw UnsupportedOperationException()
                })
                list
            }

        private fun parseNodes(lines: List<String>): Map<String, Pair<String, String>> =
            lines.mapNotNull(nodeRegex::find)
                .map(MatchResult::groupValues)
                .filter { list -> list.size == 4 }
                .associate { groupValues ->
                    groupValues[1] to (groupValues[2] to groupValues[3])
                }
    }

    fun getInstruction(index: Int): Instruction =
        instructions[index % instructions.size]

    fun getSteps(): Int {
        var currentIndex = "AAA"
        var steps = 0
        while (currentIndex != "ZZZ") {
            currentIndex = step (currentIndex, getInstruction(steps))
            steps++
        }
        return steps
    }

    val startingNodes: Collection<String>
        get() = nodeMap.keys.filter { key ->
            key.endsWith('A')
        }


    fun getStepsAllStartingNodes(): Int {
        var currentNodes = startingNodes
        var steps = 0
        while (!currentNodes.all { node ->
            node.endsWith('Z')
            }) {
            currentNodes = currentNodes.map { nodeKey ->
                step(nodeKey, getInstruction(steps))
            }
            steps++
        }
        return steps
    }

    private fun step(key : String, instruction: Instruction): String {
        val node = nodeMap[key]!!
        return when (instruction) {
            Instruction.LEFT -> node.first
            Instruction.RIGHT -> node.second
        }
    }

}
