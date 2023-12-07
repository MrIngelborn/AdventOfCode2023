import java.util.Objects

class Card(val value: Char) : Comparable<Card> {
    override operator fun compareTo(other: Card): Int {
        return intValue.compareTo(other.intValue)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Card)  {
            return Objects.equals(value, other.value)
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    private val intValue
        get() = when (value) {
            in '2'..'9' -> value-'0'
            'T' -> 10
            'J' -> 11
            'Q' -> 12
            'K' -> 13
            'A' -> 14
            else -> 0
        }

    override fun toString(): String {
        return "$value"
    }
}