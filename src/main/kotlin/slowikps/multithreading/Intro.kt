package slowikps.multithreading

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking { // doesn't stop until all coroutine are done
    (0..20).map {
        launch { // launch a new coroutine in background and continue
            delay(1000L) // !!!non-blocking!!! delay for 1 second (default time unit is ms)
            println("$it: World! Job: ${coroutineContext[Job]}, isJobActive: ${coroutineContext[Job]?.isActive}}")
        }
    }.forEach {
        it.join() // no need to do that as we run in the runBlocking context
    }

    println("Hello,")
    Thread.sleep(1L) // !!!block!!! main thread

    orderTest()
}

fun orderTest() = runBlocking {
    withLaunch()

    coroutineScope { // Creates a coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before the nested launch
    }

    println("Coroutine scope is over") // This line is not printed until the nested launch completes
}

private fun CoroutineScope.withLaunch() {
    launch {
        delay(200L)
        println("Task from runBlocking")
    }
}

class WithScope(private val scope: CoroutineScope) {//scope is used in the suspend function

    suspend fun otherWay()  { // this: CoroutineScope
        scope.launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope { // Creates a coroutine scope
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // This line will be printed before the nested launch
        }

        println("Coroutine scope is over") // This line is not printed until the nested launch completes
    }
}