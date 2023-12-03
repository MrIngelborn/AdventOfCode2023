package gear_ratios

import java.io.File

const val inputFile = "input.txt"
fun main() {
    val schematic = Schematic.parseLines(getData())
    val numbersWithSymbols = schematic.numbers.filter(Number::hasSymbol)
    numbersWithSymbols.map(Number::value)
            .sum()
            .let { println(it) }


    val symbolsWithExactlyNumbers = schematic.symbols.filter { symbol: Symbol -> symbol.numbers.size == 2 }
    symbolsWithExactlyNumbers.map { symbol: Symbol ->
        symbol.numbers.map(Number::value).reduce{ a, b -> a * b }
    }
            .sum()
            .let { println(it) }
}
fun getData() =
        object {}.javaClass.getResource(inputFile)?.toURI()!!.let(::File).readLines()