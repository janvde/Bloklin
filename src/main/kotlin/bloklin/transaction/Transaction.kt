package bloklin.transaction

import bloklin.utils.HashUtil
import bloklin.utils.SignatureUtil
import java.security.PrivateKey
import java.security.PublicKey

/**
 * https://blockgeeks.com/bitcoin-transactions/
 */
class Transaction(
        val sender: PublicKey,
        val reciepient: PublicKey,
        val value: Float,
        val transactionInputs: List<TransactionInput> = emptyList()) {

    val transactionOutputs: List<TransactionInput> = emptyList()


    //vin_sz: The number of inputs to this transaction.
    // Similarly, vout_sz counts the number of outputs.
    //lock_time: this basically describes the earliest time at which a block can be added to the blockchain.
    // It is either the block height or a unix timestamp.
    var transactionHash: String? = null //hash of this transaction
    var signature: ByteArray? = null


    /**
     * sign the hash of this transaction with a private key
     * signature is stored in the transaction
     */
    fun sign(privateKey: PrivateKey) {
        val message = calculateHash()
        signature = SignatureUtil.signWithECDSA(privateKey, message)
    }

    fun verify() {

    }


    /**
     * process this transaction.
     * return false if fails
     */
    fun process(): Boolean {
        //get transaction inputs
        //check if transactions are valid
        //generate output transactions
        return true
    }

    fun calculateHash(): String {
        //todo should add more to prevent transactions from having the same hash
        return HashUtil.sha256("${sender.encoded}${reciepient.encoded}${value.toString()}")
    }
}