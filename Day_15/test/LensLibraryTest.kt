import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LensLibraryTest {

    private lateinit var initSequence: InitSequence
    private val steps
        get() = initSequence.steps

    @BeforeEach
    fun createSequence() {
        val inputParser = InputParser("test_input.txt")
        initSequence = InitSequence.parseLines(inputParser.lines)
    }

    @Test
    fun canParseData() {
        assertEquals(11, steps.size)

        val strings: List<String> = steps.map { step -> step.toString() }
        assertEquals("rn=1", strings[0])
        assertEquals("cm-", strings[1])
        assertEquals("ot=7", strings[10])
    }

    @Test
    fun canHashString() {
        val hashValue: Int = Hasher.hash("HASH")
        assertEquals(52, hashValue)
    }

    @Test
    fun canHashStep() {
        assertEquals(30, steps[0].hashValue)
    }


    @Test
    fun canCalculateHashSum() {
        assertEquals(1320, initSequence.hashSum)
    }

    @Test
    fun canParseStepOperation() {
        assertEquals(Operation.ADD, steps[0].operation)
        assertEquals(Operation.DELETE, steps[1].operation)
        assertEquals(Operation.ADD, steps[2].operation)
    }

    @Test
    fun canParseStepLabel() {
        assertEquals("rn", steps[0].label)
        assertEquals("cm", steps[1].label)
        assertEquals("qp", steps[2].label)
    }

    @Test
    fun canParseStepFocalLength() {
        assertEquals(1, steps[0].focalLength)
        assertEquals(null, steps[1].focalLength)
        assertEquals(3, steps[2].focalLength)
    }

    @Test
    fun canAddLensToBox() {
        val box = Box()
        box.add(Step.create("rn=1"))
        assertEquals(1, box.size())
        box.add(Step.create("qp=3"))
        assertEquals(2, box.size())

        assertEquals(1, box[0].focalLength)
        assertEquals(3, box[1].focalLength)
    }

    @Test
    fun canRemoveLensFromBox() {
        val box = Box()
        box.add(Step.create("rn=1"))
        box.add(Step.create("rn-"))
        assertEquals(0, box.size())
    }

    @Test
    fun canReplaceLensInBox() {
        val box = Box()
        box.add(Step.create("rn=1"))
        box.add(Step.create("qp=3"))
        box.add(Step.create("rn=2"))

        assertEquals(2, box.size())
        assertEquals(2, box[0].focalLength)
        assertEquals(3, box[1].focalLength)
    }

    @Test
    fun canApplyLensToBoxes() {
        val boxes = Boxes()
        boxes.applyStep(steps[0])
        boxes.applyStep(steps[1])
        boxes.applyStep(steps[2])

        val lens1: Step = boxes[0][0]
        assertEquals(steps[0], lens1)

        val lens2: Step = boxes[1][0]
        assertEquals(steps[2], lens2)
    }

    @Test
    fun canCalculateFocusPower() {
        val boxes = Boxes()
        boxes.applySteps(initSequence.steps)
        assertEquals(145, boxes.focusPower)
    }
}