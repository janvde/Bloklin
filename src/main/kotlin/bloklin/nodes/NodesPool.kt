package bloklin.nodes

object NodesPool {
    val nodes = mutableSetOf<Node>() //set of nodes in the pool


    /**
     * add a node to the pool
     */
    fun addNode(node: Node){
        println("addNode: ${node}")
        if(!node.isValidAddress()){
            throw Exception("Invalid address")
        }
        if(!node.isValidPort()){
            throw Exception("Invalid port")
        }

        nodes.add(node)
    }

    /**
     * add a list of nodes to the pool
     */
    fun addNodes(nodeList: List<Node>){
        nodeList.forEach {
            addNode(it)
        }
    }
}