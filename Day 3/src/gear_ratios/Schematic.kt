package gear_ratios

import java.util.ArrayList
import kotlin.streams.toList

class Schematic(val numbers: Collection<Number>, val symbols: Collection<Symbol>) {
 companion object Factory {
     val numbers = ArrayList<Number>()
     val symbols = ArrayList<Symbol>()
     fun parseLines(lines: Collection<String>): Schematic {
         numbers.clear()
         symbols.clear()

         lines.forEachIndexed { y, line ->
            var numberValue: Int? = null
            line.toList().forEachIndexed { x, char ->
                when (char) {
                    in '0'..'9' -> numberValue = addCharToNumber(numberValue, char)
                    '.' -> finilizeNumberIfExists(number, x, y)
                    else -> addSymbol(char, x, y)
                }
            }
         }

         return Schematic(numbers, symbols)
     }

     private fun addCharToNumber(numberValue: Int?, char: Char): Int? {

     }
 }
}