package bloklin

import bloklin.chain.Block
import bloklin.chain.BlockChain
import bloklin.nodes.Node
import bloklin.nodes.NodesPool
import bloklin.remote.NodeServiceFactory
import io.reactivex.rxkotlin.subscribeBy

object Bloklin {
    var blockChain: BlockChain = BlockChain()
    val dificulty = 2


    fun mineBlock(data: Any): Block {
        if (blockChain.isEmpty()) {
            /**
             * add a genesis block to the chain with hardcoded data in it
             */
            val genesisBlock = Block(0, "0", "genesis data")
            //val genesisBlock = Block(0, HashUtil.sha256("genesis block"), "genesis data", nonce = 1)
            blockChain.addBlock(genesisBlock)
        }

        val lastBlock = blockChain.lastBlock()

        val lastBlockIndex = lastBlock.index
        val previousHash = lastBlock.hash

        val block = Block(lastBlockIndex + 1, previousHash, data)
        block.mine(dificulty)
        /*val pow = proofOfWork(block)
        block.nonce = pow
        block.hash = block.calculateHash()*/

        blockChain.addBlock(block)
        return block


    }


    fun downloadNodes(){

    }

    /**
     * replace our chain with the longest chain in the network
     * todo should be chain with the most pow instead of the longest
     */
    fun consensus(){
        val nodesPool = NodesPool

        for (node: Node in nodesPool.nodes) {
            //fetch chain from node

            val url = "${node.ip}:${node.port}"
            val nodeService = NodeServiceFactory.makeNodeService(url)
            nodeService.getChain()
                    .subscribeBy(
                            onSuccess = {
                                //if their chain is longer replace our chain
                                if (blockChain.size() < it.size()) {
                                    if (it.isChainValid()) {
                                        blockChain = it
                                        println("our chain is replaced by longer chain of $url")
                                    } else {
                                        println("chain of $url is not valid")
                                    }
                                }
                            },
                            onError = { it.printStackTrace() }
                    )
        }
    }

}