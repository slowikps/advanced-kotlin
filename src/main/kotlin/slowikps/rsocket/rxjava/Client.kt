package slowikps.rsocket.rxjava

import io.reactivex.Flowable
import io.rsocket.kotlin.DefaultPayload
import io.rsocket.kotlin.Payload
import io.rsocket.kotlin.RSocketFactory
import io.rsocket.kotlin.transport.netty.client.TcpClientTransport

fun main() {
    val client = Client()
    println(client)


    System.`in`.read()
}

class Client {
    private val client = RSocketFactory               // Requester RSocket
        .connect()
        .transport(
            TcpClientTransport       // WebSockets transport
                .create(
                    Server.port
                )
        )
        .start()
        .map { it.requestStream(DefaultPayload.text("Boom")) }
        // .map { println(it) }
        .subscribe{
            msg: Flowable<Payload> ->
                println("I got: $msg")
                msg.subscribe {
                    another -> println(another.dataUtf8)
                }
        }

    fun printSth() {
        println(client)
    }


}