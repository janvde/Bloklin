package bloklin.transaction

import java.security.PublicKey

/**
 * @param value: the amount of coins being spend
 * @param recipient: the key of a recipient
 */
class TransactionOutput(val value: Float, val recipient: PublicKey)