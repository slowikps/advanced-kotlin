package slowikps.rsocket

import io.reactivex.Flowable
import io.reactivex.Single
import io.rsocket.kotlin.Closeable
import io.rsocket.kotlin.DefaultPayload
import io.rsocket.kotlin.Payload
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.RSocketFactory

import io.rsocket.kotlin.Setup
import io.rsocket.kotlin.transport.netty.server.NettyContextCloseable
import io.rsocket.kotlin.transport.netty.server.WebsocketServerTransport
import io.rsocket.kotlin.util.AbstractRSocket
import okhttp3.HttpUrl
import java.util.concurrent.TimeUnit

fun main() {
    val server = Server()
    val client = Client()
    println(server)
    println(client)
    server.doSomething()
    client.sendMessage("Abc")
    TimeUnit.SECONDS.sleep(5)
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

    val server: Single<NettyContextCloseable> = RSocketFactory
        .receive()
        .acceptor { { setup, rSocket -> handler(setup, rSocket) } } // server handler RSocket
        .transport(WebsocketServerTransport.create(port))  // Netty websocket transport
        .start()


    private fun handler(setup: Setup, rSocket: RSocket): Single<RSocket> {
        return Single.just(object : AbstractRSocket() {
            override fun requestStream(payload: Payload): Flowable<Payload> {
                println("[Server] I got: $payload")
                return Flowable.just(DefaultPayload.text("server handler response"))
            }
        })
    }

    fun doSomething() {
        server.blockingGet()
    }
}