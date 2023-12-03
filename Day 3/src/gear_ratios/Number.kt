package gear_ratios

class Number(val value: Int, var startPositionXY: Pair<Int,Int>) {
    private val symbols: MutableSet<Symbol> = HashSet()
    fun hasSymbol(): Boolean = symbols.isNotEmpty()
    fun addSymbol(symbol: Symbol) {
        symbols.add(symbol)
    }
}