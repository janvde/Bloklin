package bloklin
import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import bloklin.nodes.Node
import bloklin.nodes.NodesPool

fun main(args: Array<String>) {
    val blockChain = BlockChain
    val nodes = NodesPool

    embeddedServer(Netty, 8080, watchPaths = listOf("ServerKt"), module = Application::module).start()
    nodes.addNode(Node("127.0.0.1", 8080))

    for (i in 1..5) {
        /*val block = bloklin.Block(i, bloklin.getBlockChain.lastBlock().hash, i, 0, nonce = 0)
        bloklin.getBlockChain.addBlock(block)*/
        BlockChain.mineBlock("some data")
    }

    println("is chain valid: "+ BlockChain.isChainValid())
}
