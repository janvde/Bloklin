object DificultyUtil {
    fun getDificultyString(dificulty: Int): String {
        var result = "0"
        while (result.length < dificulty) result = result + "0"
        return result
    }
}