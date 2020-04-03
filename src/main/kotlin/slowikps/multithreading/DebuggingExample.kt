package slowikps.multithreading

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * There are three coroutines.
 * The main coroutine (#1) inside runBlocking
 *  and two coroutines computing the deferred values a (#2) and b (#3).
 * They are all executing in the context of runBlocking and are confined to the main thread.
 */
//-Dkotlinx.coroutines.debug JVM option:
fun main() = runBlocking {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")