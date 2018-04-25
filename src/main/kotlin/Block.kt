import utils.HashUtil
import java.util.*

class Block(
        val index: Int, //index of the block in the chain. Increments with each block.
        val previousHash: String, //hash of the previous block header
        var data: Any, //any data we want to store in this block
        val timeStamp: Long = Date().time, //unix timestamp of this block
        var nonce: Int = 1
) {

    val hash = calculateHash()


    //calculate hash of this block
    fun calculateHash(): String = HashUtil.sha256(previousHash + data + timeStamp + nonce)



    fun isGenesisBlock(): Boolean {
        return index == 0
    }

    override fun toString(): String {
        return "Block(index=$index, previousHash='$previousHash', data=$data, timeStamp=$timeStamp, nonce=$nonce, hash='$hash')"
    }

}
