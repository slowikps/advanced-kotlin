package slowikps.plain

fun main() {
    val tmp: List<String> = (1..10).map {
        println(it)
        "it: $it"
    }.map {
        println(it)
        "map2: $it"
    }
    println("The end")
}