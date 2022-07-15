//package slowikps.rsocket.rxjava
//
//import io.reactivex.Flowable
//import io.rsocket.kotlin.DefaultPayload
//import io.rsocket.kotlin.Payload
//import io.rsocket.kotlin.RSocket
//import io.rsocket.kotlin.RSocketFactory
//import io.rsocket.kotlin.payload.Payload
//import io.rsocket.kotlin.transport.netty.client.TcpClientTransport
//
//fun main() {
//    val client = Client()
//    client.subscribe()
//
//
//    System.`in`.read()
//}
//
//class Client {
//    private val client: RSocket = RSocketFactory               // Requester RSocket
//        .connect()
//        .transport(
//            TcpClientTransport       // WebSockets transport
//                .create(
//                    Server.port
//                )
//        )
//        .start()
//        .blockingGet()
//
//    fun subscribe() {
//        println("about to request stream")
//        val requested: Flowable<Payload> = client.requestStream(DefaultPayload.text("Gimme: [59]"))
//        requested.subscribe {
//            println(it.dataUtf8)
//        }
//    }
//}