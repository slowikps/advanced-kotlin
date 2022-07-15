//package slowikps.rsocket.rxjava
//
//import io.reactivex.Flowable
//import io.reactivex.Single
//import io.rsocket.kotlin.DefaultPayload
//import io.rsocket.kotlin.Payload
//import io.rsocket.kotlin.RSocket
//import io.rsocket.kotlin.RSocketError
//import io.rsocket.kotlin.RSocketFactory
//import io.rsocket.kotlin.Setup
//import io.rsocket.kotlin.payload.Payload
//import io.rsocket.kotlin.transport.netty.server.NettyContextCloseable
//import io.rsocket.kotlin.transport.netty.server.TcpServerTransport
//import io.rsocket.kotlin.util.AbstractRSocket
//import okhttp3.HttpUrl
//import java.util.concurrent.TimeUnit
//
//fun main() {
//    val server = Server()
//    println(server)
//
//    System.`in`.read()
//}
//
//class Server {
//    companion object {
//        val scheme: String = "http"
//        val host: String = "127.0.0.1"
//        val port: Int = 8093
//
//        fun getUrl() =
//            HttpUrl.Builder()
//                .scheme(scheme)
//                .host(host)
//                .port(port)
//                .build()
//    }
//
//    private val server: Single<NettyContextCloseable> = RSocketFactory
//        .receive()
//        /*RSocket is symmetric so both client and server have can have Client(requester) to make requests
//        * rSocket parameter can be used to talk to client
//        */
//        .acceptor { { setup, rSocket -> handler(setup, rSocket) } }
//        .transport(TcpServerTransport.create(port))  // Netty websocket transport
//        .start()
//
//    init {
//        server.blockingGet()
//    }
//
//    private fun handler(setup: RSocketError.Setup, rSocket: RSocket): Single<RSocket> {
//        println("setup $setup")
//        println("rSocket $rSocket")
//        return Single.just(object : AbstractRSocket() {
//            override fun requestStream(payload: Payload): Flowable<Payload> {
//                println("[Server] I got: ${payload.dataUtf8}")
//                val regex = """Gimme: \[(\d+)\]""".toRegex()
//
//                val take: Int = regex.find(payload.dataUtf8)?.groupValues?.get(1)?.toInt() ?: 10
//                return Flowable.interval(1, TimeUnit.SECONDS)
//                    .map { DefaultPayload.text("server's message $it") }
//                    .take(take.toLong() )
//            }
//        })
//    }
//}