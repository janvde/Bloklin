import java.util.*

class Block(
        val index: Int, //index of the block in the chain. Increments with each block.
        val previousHash: String, //hash of the previous block header
        val data: Any, //any data we want to store in this block
        val timeStamp: Long = Date().time, //unix timestamp of this block
        val nonce: Int
) {

    val hash = calculateHash()


    //calculate hash of this block
    private fun calculateHash(): String = HashUtil.sha256(previousHash + data + timeStamp + nonce)


    override fun toString(): String {
        return "Block: ${hash} ${data.toString()}"
    }
}
