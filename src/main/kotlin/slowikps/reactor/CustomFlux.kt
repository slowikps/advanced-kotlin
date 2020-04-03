package slowikps.reactor

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.SynchronousSink

fun main() = runBlocking {
    // fluxGenerateExample()
    fluxCreateExample()
    println("The end of main")
}

fun fluxGenerateExample() {
    val flux = Flux.generate(
        { 0 },
        { state: Int, sink: SynchronousSink<String?> ->
            "3 x $state = ${3 * state}".let {
                println("[Generator] About to emit: $it")
                sink.next(it)
            }
            if (state == 10) sink.complete()
            state + 1
        },
        { endState -> println("[Generator] endState: $endState") }
    )

    flux.subscribe(PrintlnConsumer("First"))
    flux.subscribe(PrintlnConsumer("Second"))
}

suspend fun fluxCreateExample() = coroutineScope {
    val flux =
        Flux.create { sink: FluxSink<String?> ->
            EventGenerator.register(
                object : EventConsumer {
                    override fun accept(event: String): Unit = sink.run { next(event) }

                    override fun complete(): Unit = sink.run { complete() }
                })
        }

    println("Before starting generate")
    async {
        EventGenerator.generate(10)
    }
    println("After starting generate")
    flux.subscribe(PrintlnConsumer("First"))
    delay(100)
    flux.subscribe(PrintlnConsumer("Second"))
}

object EventGenerator {
    private val consumers = mutableListOf<EventConsumer>()
    suspend fun generate(max: Int): Unit {
        if (max > 0) {
            delay(35)
            val msg = "Event: $max"
            println("[Generator] $msg")
            consumers.forEach { it.accept(msg) }

            generate(max - 1)
        } else {
            consumers.forEach { it.complete() }
        }
    }

    fun register(consumer: EventConsumer) {
        consumers += consumer
    }
}

interface EventConsumer {
    fun accept(event: String)
    fun complete()
}