package slowikps.reactor

import java.util.function.Consumer

class PrintlnConsumer<T>(private val prefix: String): Consumer<T?> {
    override fun accept(t: T?) {
        println("[$prefix] $t")
    }
}