package cube_conundrum

class Game(val gameId: Int, val sets: Collection<Set>) {

    companion object Factory {
        private val regex = Regex("Game (\\d+): (.*)")

        fun create(line: String): Game {
            val groups = regex.find(line)?.groups
            val gameId = groups?.get(1)?.value?.let(Integer::parseInt)
            val setsString = groups?.get(2)?.value
            val sets = setsString?.split(';')?.map(Set::create)

            return Game(gameId !!, sets !!);
        }
    }

    fun isPossible(red: Int, green: Int, blue: Int): Boolean =
            sets.map { set -> set.isPossible(red, green, blue) }.reduce(Boolean::and)

    override fun toString(): String {
        return "Game $gameId: $sets"
    }
}