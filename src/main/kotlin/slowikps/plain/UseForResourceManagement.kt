package slowikps.plain

import kotlinx.coroutines.newSingleThreadContext

fun main() {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        println("!!Use!! function can be called with Closable to properly manage resources")
    }
}