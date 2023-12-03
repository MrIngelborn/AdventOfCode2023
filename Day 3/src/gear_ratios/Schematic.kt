package gear_ratios

import java.util.ArrayList

class Schematic(val numbers: Collection<Number>, val symbols: Collection<Symbol>) {
    companion object Factory {
        val numbers = ArrayList<Number>()
        val symbols = ArrayList<Symbol>()
        fun parseLines(lines: Collection<String>): Schematic {
            numbers.clear()
            symbols.clear()

            lines.forEachIndexed { y, line ->
                var numberValue: Int? = null
                var numberStart: Int? = null
                line.toList().forEachIndexed { x, char ->
                    when (char) {
                        in '0'..'9' -> {
                            numberValue = addCharToNumber(numberValue, char)
                            numberStart = numberStart ?: x
                        }
                        '.' -> {
                            numberValue?.run {
                                finalizeNumber(numberValue !!, numberStart !!, y)
                                numberValue = null
                                numberStart = null
                            }
                        }
                        else -> addSymbol(char, x, y)
                    }
                }
                numberValue?.run {finalizeNumber(numberValue !!, numberStart !!, y) }
            }

            return Schematic(numbers, symbols)
        }

        private fun addCharToNumber(numberValue: Int?, char: Char): Int =
                (numberValue ?: 0) * 10 + (char - '0')

        private fun finalizeNumber(numberValue: Int, x: Int, y: Int) {
            numbers.add(Number(numberValue, x to y))
        }

        private fun addSymbol(char: Char, x: Int, y: Int) {
            symbols.add(Symbol(char, x to y))
        }
    }
}