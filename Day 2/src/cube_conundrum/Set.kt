package cube_conundrum

class Set(val red: Int, val green: Int, val blue: Int) {
    companion object Factory {
        val regex = Regex("(\\d+) (green|blue|red)")

        fun create(setString: String): Set {
            val matches = regex.findAll(setString)

            var red = 0
            var green = 0
            var blue = 0

            for (match in matches) {
                val amount = match.groups.get(1)?.value.let(Integer::parseInt)
                val color = match.groups.get(2)?.value
                when (color) {
                    "red" -> red += amount
                    "green" -> green += amount
                    "blue" -> blue += amount
                }
            }

            return Set(red, green, blue)
        }
    }

    fun isPossible(red: Int, green: Int, blue: Int): Boolean =
        red >= this.red
                && green >= this.green
                && blue >= this.blue

    override fun toString(): String {
        return "$red red, $green green, $blue blue; "
    }

}