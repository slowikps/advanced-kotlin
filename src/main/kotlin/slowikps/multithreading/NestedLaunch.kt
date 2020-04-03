package slowikps.multithreading

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

//Essentially, coroutines are light-weight threads. They are launched with launch coroutine builder in a context of some CoroutineScope.

//We can launch coroutines in this scope without having to join them explicitly, because an outer coroutine (runBlocking in our example) does not complete until all the coroutines launched in its scope complete.
fun main22(): Unit = GlobalScope.launch {
    coroutineScope {
        println("Level 1: starts")
        coroutineScope {//It creates a coroutine scope and does not complete until all launched children complete.
            launch {
                delay(100)
                println("Level 2a: in launch")
            }
            println("Level 2a: before delay")
            delay(40)
            println("Level 2a: ends")
        }
        coroutineScope {
            launch {
                delay(50)
                println("Level 2b: in launch")
            }
            println("Level 2b: before delay")
            delay(20)
            println("Level 2b: before delay")
        }
        println("Level 1: before delay")
        delay(10)
        println("Level 1: ends")
    }

    println("The end")
}.run { TimeUnit.SECONDS.sleep(2) }

fun main1() = runBlocking<Unit> { // start main coroutine
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    } //Main coroutine is not waiting for this to finish as it is using different context???
    println("Hello,") // main coroutine continues here immediately
    delay(500L)      // delaying for 2 seconds to keep JVM alive
}

fun main() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking") //2
    }

    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")//3
        }

        delay(100L)
        println("Task from coroutine scope") //1
    }

    println("Coroutine scope is over") //4
}