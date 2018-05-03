package bloklin.transaction

import java.security.Signature

/**
 * @param previousOutputHash: This is a hash pointer to a previously unspent transaction output (UTXO).
 * Essentially, this is money that belongs to you that you are about to spend in this transaction.
 *
 * @param index: An index into the list of outputs of the previous transaction.
 * This is the actual output that you are spending.
 *
 * @param signature: This is a spending script that proves that the creator of this transaction has permission to spend the money
 */
class TransactionInput(val previousOutputHash: String,
                       val index: Int,
                       val signature: Signature
                       )