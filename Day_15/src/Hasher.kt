class Hasher(val strings: List<String>) {

    companion object Factory {
        fun parseLines(lines: List<String>): Hasher {
            val strings = lines.flatMap { line ->  line.split(',') }
            return Hasher(strings)
        }
    }

    fun hash(string: String): Int =
        string.fold(0) { acc, char ->
            ((acc + char.code) * 17) % 256
        }

    val hashSum: Int
        get() = strings.map(::hash).sum()

}
