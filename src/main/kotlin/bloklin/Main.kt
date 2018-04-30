package bloklin

import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {

    var port: Int = 8080

    if(args.size > 0){
        port = args[0].toInt()
    }

    embeddedServer(Netty, port, watchPaths = listOf("ServerKt"), module = Application::module).start()
    //NodesPool.addNode(Node("127.0.0.1", port))

    for (i in 1..200) {
        //BlockChain.mineBlock("some data")
        Bloklin.mineBlock("some datas")
    }

    println("is chain valid: " + Bloklin.blockChain.isChainValid())
}
