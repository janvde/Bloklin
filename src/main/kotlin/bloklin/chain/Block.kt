package bloklin.chain

import bloklin.transaction.Transaction
import bloklin.utils.DificultyUtil
import bloklin.utils.HashUtil
import java.util.*
import kotlin.collections.ArrayList

class Block(index: Int, previousHash: String, transactions: ArrayList<Transaction>) {

    var index: Int
    var hash: String
    var previousHash: String
    var transactions: ArrayList<Transaction> = ArrayList()
    var timeStamp: Long = 0
    var nonce: Int = 1

    init {
        this.index = index
        this.previousHash = previousHash
        this.transactions = transactions
        this.timeStamp = Date().getTime()
        this.hash = calculateHash() //Making sure we do this after we set the other values.
    }

    //calculate hash of this block
    fun calculateHash(): String = HashUtil.sha256("$index$previousHash$transactions$timeStamp$nonce")

    /**
     * simple proof of work mechanism
     * increases none until proof is valid
     * hash should start with an amount of zeros
     */
    fun mine(difficulty: Int) {
        val suffix = DificultyUtil.getDificultyString(difficulty)
        while (!hash.startsWith(suffix)) {
            nonce++
            hash = calculateHash()
        }
    }


    /**
     * add a transaction to this block
     */
    fun addTransation(transaction: Transaction) {
        if (transaction.process()) {
            transactions.add(transaction)
        } else {
            println("Failed to process transaction: $transaction")
        }

    }

    fun isValidHash(): Boolean {
        return hash.equals(calculateHash())
    }


    fun isGenesisBlock(): Boolean {
        return index == 0
    }

    fun getDificulty(): Int = DificultyUtil.getDificulty(hash)

    override fun toString(): String {
        return "Block(index=$index, previousHash='$previousHash', transactions=$transactions, timeStamp=$timeStamp, nonce=$nonce, hash='$hash')"
    }

}
