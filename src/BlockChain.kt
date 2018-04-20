object BlockChain {
    val chain = mutableListOf<Block>()
    val dificulty: Int = 1


    /**
     * add a block to the chain
     */
    fun addBlock(block: Block) {
        //todo mine block
        if (isBlockValid(block)) {
            chain.add(block)
            println(block)
        }
    }

    /**
     * get the last block.
     * used to get the previous hash when adding block to the chain
     */
    fun lastBlock(): Block {
        return chain.last()
    }

    fun isChainValid(): Boolean {
        return true
    }

    fun isBlockValid(block: Block): Boolean {
        return true
    }
}