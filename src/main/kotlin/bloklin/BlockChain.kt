package bloklin

import bloklin.nodes.Node
import bloklin.nodes.NodesPool
import bloklin.utils.DificultyUtil
import bloklin.utils.HashUtil

/**
 * @author Jan
 *
 * Blockchain singleton
 */
object BlockChain {
    val chain = mutableListOf<Block>()
    private var dificulty = 3

    init {
        /**
         * add a genesis block to the chain with hardcoded data in it
         */
        val genesisBlock = Block(0, HashUtil.sha256("genesis block"), "some data", nonce = 1)
        chain.add(genesisBlock)
    }


    /**
     * mine a new block with given data in it
     */
    fun mineBlock(data: Any): Block {
        val lastBlock = lastBlock()

        val lastBlockIndex = lastBlock.index
        val previousHash = lastBlock.hash

        val block = Block(lastBlockIndex + 1, previousHash, data)
        val pow = proofOfWork(block)
        block.nonce = pow

        addBlock(block)
        return block
    }

    /**
     * add a block to the chain
     */
    private fun addBlock(block: Block) {
        if (isBlockValid(block)) {
            chain.add(block)
            println("Block mined! ${block.hash} ${block.nonce}")
        }
    }

    /**
     * get the last block.
     * used to get the previous hash when adding block to the chain
     */
    fun lastBlock(): Block {
        return chain.last()
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
        //genesis block is always valid, but doesnt have a valid parent block
        if (block.isGenesisBlock()) return true

        val previousBlock = chain.get(block.index - 1)

        //check for valid proof
        if (isValidProof(block, block.nonce).not()) return false

        //check equal hashes
        if (block.previousHash.equals(previousBlock.hash).not()) return false

        //check incremented index
        if (block.index != previousBlock.index + 1) return false

        return true
    }


    /**
     * check if proof of a block is valid
     * check if the block with a certain nonce results in a hash which ends with an amount of zeros
     */
    private fun isValidProof(block: Block, proof: Int): Boolean {
        val suffix = DificultyUtil.getDificultyString(dificulty)
        block.nonce = proof
        return (block.calculateHash().endsWith(suffix))
    }


    /**
     * simple proof of work mechanism
     * increases none until proof is valid
     */
    private fun proofOfWork(block: Block): Int {
        println("start mining block " + lastBlock().index.plus(1))
        var proof = 1
        while (isValidProof(block, proof).not()) {
            proof++
        }

        return proof
    }


    private fun consensus() {
        val nodesPool = NodesPool

        for (node: Node in nodesPool.nodes){
            //fetch chain from node
            //compare length with our chain
        }
    }
}