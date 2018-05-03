package bloklin.transaction

import java.security.PublicKey
import java.security.Signature

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
    val signature: Signature? = null

    fun sign() {

    }

    fun verify() {

    }

    fun calculateHash(): String {
        //todo
        return ""
    }
}