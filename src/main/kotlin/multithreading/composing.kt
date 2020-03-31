package multithreading

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
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
}

//This way, if something goes wrong inside the code of the concurrentSum function and it throws an exception,
// all the coroutines that were launched in its scope will be cancelled.
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(250)
    return 20
}

suspend fun doSomethingUsefulOne(): Int {
    delay(350)
    return 10
}
