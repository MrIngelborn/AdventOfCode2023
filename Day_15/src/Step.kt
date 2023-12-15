class Step(private val string: String, val operation: Any, val label: Any, val focalLength: Int?) {

    companion object Factory {
        fun create(string: String) : Step {
            TODO()
        }
    }

    val hashValue: Int
        get() = Hasher.hash(this.toString())

    override fun toString(): String {
        return string
    }
}
