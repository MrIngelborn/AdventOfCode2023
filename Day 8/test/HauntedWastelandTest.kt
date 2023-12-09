import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

class HauntedWastelandTest {

    @Nested
    inner class Input1 {
        private val testFile = "test_input1.txt"
        private val testData1Lines
            get() = object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

        private lateinit var network : Network

        @BeforeEach
        fun createNetwork() {
            network = Network.create(testData1Lines)
        }

        @Test
        fun canParseInstructions() {
            assertEquals(listOf(Instruction.RIGHT, Instruction.LEFT), network.instructions.toList())
        }

        @Test
        fun instructionsAreCyclical() {
            assertEquals(Instruction.RIGHT, network.getInstruction(0))
            assertEquals(Instruction.LEFT, network.getInstruction(1))
            assertEquals(Instruction.RIGHT, network.getInstruction(2))
        }

        @Test
        fun canParseNodes() {
            val nodeMap : Map<String,Pair<String,String>> = network.nodeMap

            assertEquals(7, nodeMap.size)

            assertEquals("BBB", nodeMap["AAA"]?.first)
            assertEquals("CCC", nodeMap["AAA"]?.second)
        }

        @Test
        fun canCalculateSteps() {
            assertEquals(2, network.getSteps())
        }
    }

    @Nested
    inner class Input2 {
        private val testFile = "test_input2.txt"
        private val testData1Lines
            get() = object {}.javaClass.getResource(testFile)?.toURI()!!.let(::File).readLines()

        private lateinit var network : Network

        @BeforeEach
        fun createNetwork() {
            network = Network.create(testData1Lines)
        }

        @Test
        fun canParseInstructions() {
            assertEquals(listOf(Instruction.LEFT, Instruction.LEFT, Instruction.RIGHT), network.instructions.toList())
        }
        @Test
        fun canParseNodes() {
            val nodeMap : Map<String,Pair<String,String>> = network.nodeMap

            assertEquals(3, nodeMap.size)

            assertEquals("BBB", nodeMap["AAA"]?.first)
            assertEquals("BBB", nodeMap["AAA"]?.second)
        }

        @Test
        fun canCalculateSteps() {
            assertEquals(6, network.getSteps())
        }
    }

}