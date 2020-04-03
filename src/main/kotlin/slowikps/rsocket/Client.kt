package slowikps.rsocket

import io.reactivex.Flowable
import io.reactivex.Single
import io.rsocket.kotlin.DefaultPayload
import io.rsocket.kotlin.Payload
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.RSocketFactory
import io.rsocket.kotlin.util.AbstractRSocket
import io.rsocket.transport.okhttp.client.OkhttpWebsocketClientTransport

class Client {
    val client: Single<RSocket> = RSocketFactory               // Requester RSocket
        .connect()
        .acceptor { { requesterRSocket -> handler(requesterRSocket) } }  // Optional handler RSocket
        .transport(
            OkhttpWebsocketClientTransport       // WebSockets transport
                .create(
                    Server.getUrl()
                )
        )
        .start()

    private fun handler(requester: RSocket): RSocket {
        return object : AbstractRSocket() {
            override fun requestStream(payload: Payload): Flowable<Payload> {
                println("[Client] I got: $payload")
                return Flowable.just(DefaultPayload.text("client handler response"))
            }
        }
    }

    fun sendMessage(msg: String) {
        val request = DefaultPayload.text(msg, "metadata")
        println(
            client.blockingGet()
        )
    }
}