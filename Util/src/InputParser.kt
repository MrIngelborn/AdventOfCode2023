import java.io.File

class InputParser(private val fileName: String) {
    val lines
        get() = object {}.javaClass.getResource(fileName)?.toURI()!!.let(::File).readLines()

}