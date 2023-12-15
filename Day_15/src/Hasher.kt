class Hasher(val strings: List<String>) {

    companion object {
        fun hash(string: String): Int =
            string.fold(0) { acc, char ->
                ((acc + char.code) * 17) % 256
            }
    }

}
