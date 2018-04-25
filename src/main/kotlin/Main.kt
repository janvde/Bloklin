
import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import nodes.Node
import nodes.NodesPool

fun main(args: Array<String>) {
    val blockChain = BlockChain
    val nodes = NodesPool

    embeddedServer(Netty, 8080, watchPaths = listOf("ServerKt"), module = Application::module).start()
    nodes.addNode(Node("127.0.0.1", 8080))

    for (i in 1..5) {
        /*val block = Block(i, blockChain.lastBlock().hash, i, 0, nonce = 0)
        blockChain.addBlock(block)*/
        blockChain.mineBlock("some data")
    }

    println("is chain valid: "+blockChain.isChainValid())
}
