import java.io.File


private const val inputFileName = "input.txt"

private fun getInputLines() =
    object {}.javaClass.getResource(inputFileName)?.toURI()!!.let(::File).readLines()

fun main() {
    val timeSheet = TimeSheet.create(getInputLines())
    timeSheet.numberOfWaysToWinProduct.let(::println)
    val timeSheetMerged = TimeSheet.createMerged(getInputLines())
    timeSheetMerged.numberOfWaysToWinProduct.let(::println)
}