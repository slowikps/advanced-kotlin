package slowikps.rsocket

import io.reactivex.Flowable
import io.reactivex.Single
import io.rsocket.kotlin.DefaultPayload
import io.rsocket.kotlin.Payload
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.RSocketFactory
import io.rsocket.kotlin.Setup
import io.rsocket.kotlin.transport.netty.server.NettyContextCloseable
import io.rsocket.kotlin.transport.netty.server.TcpServerTransport
import io.rsocket.kotlin.util.AbstractRSocket
import okhttp3.HttpUrl
import java.util.concurrent.TimeUnit

fun main() {
    val server = Server()
    println(server)
    server.doSomething()

    System.`in`.read()
}

class Server {
    companion object {
        val scheme: String = "http"
        val host: String = "127.0.0.1"
        val port: Int = 8093

        fun getUrl() =
            HttpUrl.Builder()
                .scheme(scheme)
                .host(host)
                .port(port)
                .build()
    }

    private val server: Single<NettyContextCloseable> = RSocketFactory
        .receive()
        .acceptor { { setup, rSocket -> handler(setup, rSocket) } } // server handler RSocket
        .transport(TcpServerTransport.create(port))  // Netty websocket transport
        .start()

    private fun handler(setup: Setup, rSocket: RSocket): Single<RSocket> {
        return Single.just(object : AbstractRSocket() {
            override fun requestStream(payload: Payload): Flowable<Payload> {
                println("[Server] I got: $payload")
                return Flowable.interval(1, TimeUnit.SECONDS)
                    .map { DefaultPayload.text("server's message $it") }
            }
        })
    }

    fun doSomething() {
        server.blockingGet()
    }
}