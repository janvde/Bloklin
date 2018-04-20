fun main(args: Array<String>) {
    val blockChain = BlockChain

    val genesisBlock = Block(HashUtil.sha256("genesis block"), "some data")
    blockChain.addBlock(genesisBlock)

    for (i in 1..5){
        val block = Block(blockChain.lastBlock().hash, i)
        blockChain.addBlock(block)
    }
}
