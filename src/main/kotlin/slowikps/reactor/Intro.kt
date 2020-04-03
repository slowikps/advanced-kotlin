package slowikps.reactor

import org.reactivestreams.Subscription
import reactor.core.Disposable
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * TODO: test the below batching with the custom geneator:
 * 0) Prefetch, buffer(x), request(x), limitRate(x) <- batching and provides the way to optimise publisher - consumer communication
 *    These operators usually also implement a replenishing optimization: Once the operator has seen 75% of the prefetch request fulfilled,
 *    it re-requests 75% from upstream. This is a heuristic optimization made so that these operators proactively anticipate the upcoming requests.
 * 0)
 */
fun main() {
    justExample()
    subscriberExample()

    val iterable = listOf("foo", "bar", "foobar")
    val seq2 = Flux.fromIterable(iterable)

    val noData = Mono.empty<String>()
    noData.subscribe(PrintlnConsumer("noData"))

    val data = Mono.just("foo")

    val numbersFromFiveToSeven = Flux.range(5, 3)
}

fun justExample() {
    val seq1 = Flux.just("foo", "bar", "foobar")
        .mergeWith(Flux.error(IllegalArgumentException("Boom")))
        .mergeWith(Flux.just("afterError"))

    seq1.run {
        logError{ subscribe(PrintlnConsumer("first")) }

        subscribe(
            PrintlnConsumer("second"),
            PrintlnConsumer("error"), //Error consumer,
            Runnable { println("Done") } // Not called because there is an error?
        )
    }
}

fun subscriberExample() {
    class SampleSubscriber<T>(private val prefix: String, private var maxToConsume: Int) : BaseSubscriber<T>() {

        override fun hookOnSubscribe(subscription: Subscription) {
            //subscription.request(Long.MAX_VALUE); <- base implementation
            println("[$prefix] Subscribed: $subscription")
            requestOneIfNeeded()
        }

        public override fun hookOnNext(value: T) {
            println("[$prefix] $value")
            requestOneIfNeeded()
        }

        private fun requestOneIfNeeded() {
            if(maxToConsume > 0) {
                request(1)
                maxToConsume -= 1
            }

        }
    }

    val firstSubscriber = SampleSubscriber<Int>("First", 5)
    val secondSubscriber = SampleSubscriber<Int>("Second", 2)
    val ints = Flux.range(1, 4)
    ints.subscribe(firstSubscriber)
    ints.subscribe(secondSubscriber)
}

fun logError(function: () -> Disposable) {
    try {
        function()
    } catch (ex: Exception) {
        println("Exception [${ex.javaClass}], message [${ex.message}], going forward")
    }
}