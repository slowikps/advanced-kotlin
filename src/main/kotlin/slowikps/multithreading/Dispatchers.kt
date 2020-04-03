package slowikps.multithreading

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main(): Unit = runBlocking {
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread, but only until the first suspension point.
        // After suspension it resumes the coroutine in the thread that is fully determined by the suspending function that was invoked.
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher, same dispatcher as GlobalScope.launch { ... }
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
    TimeUnit.SECONDS.sleep(1)
    repeat(5) {println()}
    unconfinedTest()
}.let {  }

fun unconfinedTest() = runBlocking {
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("1Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("1Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    launch { // context of the parent, main runBlocking coroutine
        println("2main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("2main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}
