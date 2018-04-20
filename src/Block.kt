import java.util.*

class Block(
        val previousHash: String, //hash of the previous block header
        val data: Any, //any data we want to store in this block
        val timeStamp: Long = Date().time //unix timestamp of this block
) {


    //calculate hash of this block
    val hash: String by lazy {
        HashUtil.sha256(previousHash + data + timeStamp)
    }

    override fun toString(): String {
        return "Block: ${hash} ${data.toString()}"
    }
}
