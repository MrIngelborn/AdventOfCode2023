class Step(val string: String) {

    companion object Factory {
        fun create(string: String) : Step {
            return Step(string)
        }
    }

    val hashValue: Int
        get() = Hasher.hash(this.toString())

    override fun toString(): String {
        return string
    }
}
