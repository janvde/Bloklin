package bloklin.transaction

import bloklin.utils.HashUtil
import java.security.PublicKey

/**
 * https://blockgeeks.com/bitcoin-transactions/
 */
class Transaction(
        val sender: PublicKey,
        val reciepient: PublicKey,
        val value: Float,
        val transactionInputs: List<TransactionInput> = emptyList()) {

    val transactionOutputs : List<TransactionInput> = emptyList()


    //vin_sz: The number of inputs to this transaction.
    // Similarly, vout_sz counts the number of outputs.
    //lock_time: this basically describes the earliest time at which a block can be added to the blockchain.
    // It is either the block height or a unix timestamp.
    var transactionHash: String? = null //hash of this transaction
    val signature: ByteArray? = null

    fun sign() {

    }

    fun verify() {

    }

    fun calculateHash(): String {
        //todo should add more to prevent transactions from having the same hash
        return HashUtil.sha256("${sender.encoded}${reciepient.encoded}${value.toString()}")
    }
}