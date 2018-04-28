package bloklin.chain

import bloklin.Bloklin

class BlockChain(var chain: MutableList<Block> = mutableListOf()) {


    /**
     * get the last block.
     */
    fun lastBlock(): Block {
        return chain.last()
    }

    fun isEmpty(): Boolean {
        return chain.isEmpty()
    }

    fun size(): Int {
        return chain.size
    }

    /**
     * add a block to the chain
     */
    fun addBlock(block: Block) {
        chain.add(block)
    }


    /**
     * check if the chain is valid
     * loop over chain to verify if each block is valid
     */
    fun isChainValid(): Boolean {

        for (block: Block in chain) {
            if (isBlockValid(block).not()) return false
        }
        return true
    }

    /**
     * check if a block is valid by:
     * 1. checking if the proof is valid
     * 2. checking if the the hashes are sequential
     * 3. checking if the indexes are sequential
     */
    fun isBlockValid(block: Block): Boolean {
        //println("isBlockValid "+block.toString())
        //genesis block is always valid, but doesnt have a valid parent block
        if (block.isGenesisBlock()) return true

        //check for valid proof
        //todo where to get dificulty from
        if (block.isValidProof(Bloklin.dificulty).not()) return false

        if(!block.isValidHash()) return false

        val previousBlock = chain.get(block.index - 1)

        //check equal hashes
        if (block.previousHash.equals(previousBlock.hash).not()) return false

        //check incremented index
        if (block.index != previousBlock.index + 1) return false

        return true
    }
}