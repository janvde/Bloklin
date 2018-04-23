fun main(args: Array<String>) {
    val blockChain = BlockChain

    for (i in 1..10) {
        /*val block = Block(i, blockChain.lastBlock().hash, i, 0, nonce = 0)
        blockChain.addBlock(block)*/
        blockChain.mineBlock("some data")
    }
}
