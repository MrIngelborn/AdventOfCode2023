package gear_ratios

class Symbol(val sign: Char, val positionXY: Pair<Int, Int>) {

    val numbers: MutableSet<Number> = HashSet()

    fun addNumber(number: Number) {
        numbers.add(number)
    }
}