package slowikps.multithreading

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.yield
import java.util.concurrent.CancellationException
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    measureTimeMillis {

        val job = launch {
            try {
                repeat(1000) {
                    print("It is x: [$it] execution [isActive: $isActive]")
                    // TimeUnit.MILLISECONDS.sleep(100) <- with this it wouldn't be
                    delay(35) //<- this makes it cancellable, exception is thrown here when coroutine is cancelled
                    println("   after delay function: $isActive")
                }
            } finally {
                // delay(1) <- throw exception immediately as this coroutine is cancelled
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
            yield() // Another way to make coroutine cancellable
            println("The end of launch") //Won't be printed
        }

        println("Main: about to sleep for 100")
        delay(100)
        println("\nMain: I am awake")
        job.cancel(CancellationException("Cancel it!")) // Still join is not getting any exception

        println("Joining: ${job.join()}")

        println("Timeout test")
        try {

            withTimeout(1300L) {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            }
        } catch(_: TimeoutCancellationException) {
            //ignoring
        }
    }.run {
        println("The end, everything took: ${this}ms")
    }
}