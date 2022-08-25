package slowikps.algo

fun main() {
    println(
        dictionary("abc def abc abc")
    )
}

fun dictionary(input: String) =
    input
        .groupBy { it }
        .filter { it.key.toString().matches("\\S".toRegex()) }
        .mapValues { it.value.size }




