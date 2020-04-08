package slowikps.plain

fun main() {
    foo()
        .take(4)
        .forEach { value -> println(value) }

    println("The end")
}

fun foo(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..10) {
        Thread.sleep(100) // pretend we are computing it
        println("About to yield $i")
        yield(i) // yield next value
    }
}