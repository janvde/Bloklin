/**
 * @author Jan
 *
 * Blockchain singleton
 */
object BlockChain {
    private val chain = mutableListOf<Block>()
    private var dificulty = 1;

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
    fun mineBlock(data: Any) {
        val lastBlock = lastBlock()

        val lastBlockIndex = lastBlock.index
        val previousHash = lastBlock.hash
        val proofOfWork = pow(lastBlock.nonce)
        val block = Block(lastBlockIndex + 1, previousHash, data, nonce = proofOfWork)

        addBlock(block)
    }

    /**
     * add a block to the chain
     */
    private fun addBlock(block: Block) {
        if (isBlockValid(block)) {
            chain.add(block)
            println("Block mined! ${block.hash} ${block.data}")
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

    /**
     * check if a block is valid by:
     * 1. checking if the proof is valid
     * 2. checking if the the hashes are sequential
     * 3. checking if the indexes are sequential
     */
    fun isBlockValid(block: Block): Boolean {
        val previousPow = lastBlock().nonce
        val validProof = isValidProof(block.nonce, previousPow)
        val sequantialHashes = block.previousHash.equals(lastBlock().hash)
        val sequantialIndexes = block.index == lastBlock().index + 1

        return validProof && sequantialHashes && sequantialIndexes
    }


    /**
     * check if a proof is valid
     * 1. multiply previous and current proof
     * 2. hash the result and convert to string
     * 3. verify if the hash ends with a zero
     */
    private fun isValidProof(previousPow: Int, proof: Int): Boolean {
        val guess = previousPow * proof
        val guessHash = HashUtil.sha256(guess.toString())
        val suffix = "0"
        return guessHash.endsWith(suffix)
    }


    /**
     * simple proof of work mechanism
     * checks which [Int] is a valid proof by increasing untill one is found
     *
     * todo don't always start at proof = 1
     * todo add dificulty
     */
    private fun pow(previousPow: Int): Int {
        println("start mining block " + lastBlock().index.plus(1))
        var proof: Int = 1
        while (isValidProof(previousPow, proof).not()) {
            proof++
        }

        return proof
    }
}