package slowikps.multithreading

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
    main2()
    val time = measureTimeMillis {
        val one: Deferred<Int> = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two: Deferred<Int> = async { doSomethingUsefulTwo() } //eager

        //it is sequential here: one is computed after two result is read
        // println("The answer is ${two.await() + one.await()}")
        one.start()
        //Not sequential anymore
        println("The answer is ${two.await() + one.await()}")
    }
    println("Completed in $time ms")

    concurrentSum(false).run {
        println("Sum: $this")
    }
    concurrentSum(true).run {
        println("Sum: $this")
    }
}

//This way, if something goes wrong inside the code of the concurrentSum function and it throws an exception,
// all the coroutines that were launched in its scope will be cancelled.
suspend fun concurrentSum(error: Boolean): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    println("started going to the next line")
    if(error)
        throw IllegalStateException("Boom")
    one.await() + two.await()
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(250)
    println("doSomethingUsefulTwo is returning result")
    return 20
}

suspend fun doSomethingUsefulOne(): Int {
    delay(350)
    println("doSomethingUsefulOne is returning result")
    return 10
}


fun main2() = runBlocking<Unit> {
    try {
        failedConcurrentSum()
    } catch(e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}