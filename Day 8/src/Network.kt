import java.util.Collections
import java.util.concurrent.atomic.AtomicBoolean
import java.util.stream.Collectors
import kotlin.concurrent.thread

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

    fun getInstruction(index: Long): Instruction =
        instructions[(index % instructions.size).toInt()]

    fun getSteps(): Long {
        var currentIndex = "AAA"
        var steps = 0L
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


    fun getStepsAllStartingNodes(): Long {
        var currentNodes = startingNodes
        val resultLists: List<MutableSet<Long>> = List(startingNodes.size) { mutableSetOf() }
        var found = AtomicBoolean()

        currentNodes.forEachIndexed { index, startNode ->
            thread(start = true) {
                var steps = 0L
                var currentNode = startNode
                while (!found.get()) {
                    currentNode = step (currentNode, getInstruction(steps))
                    steps++
                    if ( currentNode.endsWith('Z') ) {
                        resultLists[index].add(steps)
                        if (resultListIntersection(resultLists).isNotEmpty()) {
                            found.set(true)
                        }
                    }
                }
            }
        }
        return resultListIntersection(resultLists).first()
    }

    fun resultListIntersection(resultLists: List<MutableSet<Long>>) = resultLists.reduce { set, acc ->
        acc.intersect(set) as MutableSet<Long>
    }

    private fun step(key : String, instruction: Instruction): String {
        val node = nodeMap[key]!!
        return when (instruction) {
            Instruction.LEFT -> node.first
            Instruction.RIGHT -> node.second
        }
    }

}
