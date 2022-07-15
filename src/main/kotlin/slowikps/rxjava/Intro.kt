package slowikps.rxjava

import io.reactivex.rxjava3.core.Emitter
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import java.util.concurrent.TimeUnit

//Here we can control backpressure
class PrintlnSubscriber<T>(private val name: String = "") : BaseSubscriber<T>() {
    override fun hookOnComplete() = println("I am done here")
    override fun hookOnSubscribe(subscription: Subscription?) {
        println("I was just subscribed $subscription")
        subscription!!.request(Long.MAX_VALUE)
    }

    override fun hookOnNext(item: T) = println("I got: $item")
    override fun hookOnError(throwable: Throwable?) = println("Here is some error: $throwable")

    fun println(msg: String) =
        if (name != "") kotlin.io.println("[$name]$msg, [Thread: ${Thread.currentThread().name}]")
        else kotlin.io.println(msg)
}

fun main() {
    observableExample()

    TimeUnit.MILLISECONDS.sleep(500)
    flowableExamples()
}

private fun flowableExamples() {
    Flowable.just("Hello world")
        .subscribe(System.out::println)

    val flowable: Flowable<String> = Flowable.generate(object : Consumer<Emitter<String>> {
        var max = 3
        override fun accept(t: Emitter<String>?) {
            if (max > 0) {
                t?.onNext("abc: $max")
                max -= 1
            } else t?.onComplete()
        }
    })

    flowable
        .subscribeOn(Schedulers.newThread()) // .subscribeOn() Asynchronously subscribes Subscribers to the current Flowable on the specified Scheduler
        .subscribe(PrintlnSubscriber("1"))
    flowable.subscribe(PrintlnSubscriber("2"))

    Flowable.generate { t: Emitter<String>? ->
        t?.onComplete()
    }
        .subscribeOn(Schedulers.newThread())
        .subscribe(PrintlnSubscriber("Anon"))

    //Get result with blocking
    println("The result of flowable is: " +
        Flowable.range(1, 10)
            .observeOn(Schedulers.computation())
            .reduce { x, y -> x * y }
            .blockingGet()
    )
    println("The result of flowable is: " +
        Flowable.range(1, 10)
            .observeOn(Schedulers.computation())
            .map { it * it }
            .blockingLast()
    )
}

//No backpreasure
private fun observableExample() {
    Observable.create { emitter: Emitter<Long> ->
        while (true) {
            val time = System.currentTimeMillis()
            emitter.onNext(time)
            if (time % 2 != 0L) {
                emitter.onError(IllegalStateException("Odd millisecond!"))
            } else if (time % 3 != 0L) {
                break
            }
        }
    }.subscribe(
        { param -> println(param) },
        { ex -> ex.printStackTrace() }
    )
}