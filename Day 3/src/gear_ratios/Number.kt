package gear_ratios

class Number(val value: Int, startPositionXY: Pair<Int,Int>) {
    private val symbols: Set<Symbol> = HashSet()
    fun hasSymbol(): Boolean = symbols.isNotEmpty()
}