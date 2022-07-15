package slowikps.plain

fun main() {
    val input = "Gimme: [10]"
    val regex = """Gimme: \[(\d+)\]""".toRegex()

    println(regex.find(input)?.groupValues)

}