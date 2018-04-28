package bloklin.chain

import bloklin.utils.DificultyUtil
import bloklin.utils.HashUtil
import java.util.*

class Block(index: Int, previousHash: String, data: Any) {

    var index: Int
    var hash: String
    var previousHash: String
    var data: Any
    var timeStamp: Long = 0
    var nonce: Int = 1

    init {
        this.index = index
        this.previousHash = previousHash
        this.data = data
        this.timeStamp = Date().getTime()
        this.hash = calculateHash() //Making sure we do this after we set the other values.
    }

    //calculate hash of this block
    fun calculateHash(): String = HashUtil.sha256("$index$previousHash$data$timeStamp$nonce")

    /**
     * simple proof of work mechanism
     * increases none until proof is valid
     */
    fun mine(difficulty: Int) {
        val suffix = DificultyUtil.getDificultyString(difficulty)
        while (!hash.endsWith(suffix)) {
            nonce++
            hash = calculateHash()
        }
    }

    fun isValidHash(): Boolean {
        return hash.equals(calculateHash())
    }


    fun isGenesisBlock(): Boolean {
        return index == 0
    }

    override fun toString(): String {
        return "Block(index=$index, previousHash='$previousHash', data=$data, timeStamp=$timeStamp, nonce=$nonce, hash='$hash')"
    }

}
