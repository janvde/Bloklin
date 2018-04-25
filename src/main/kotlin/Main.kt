import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val blockChain = BlockChain

    embeddedServer(Netty, 8080, watchPaths = listOf("ServerKt"), module = Application::module).start()

    for (i in 1..10) {
        /*val block = Block(i, blockChain.lastBlock().hash, i, 0, nonce = 0)
        blockChain.addBlock(block)*/
        blockChain.mineBlock("some data")
    }

    println("is chain valid: "+blockChain.isChainValid())
}
