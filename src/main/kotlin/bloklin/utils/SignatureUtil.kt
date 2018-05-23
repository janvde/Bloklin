package bloklin.utils

import java.security.PrivateKey
import java.security.Signature

object SignatureUtil {

    /**
     * sign a message with a given privatekey using a given algorithm and provider
     */
    private fun sign(privateKey: PrivateKey, message: String, algorithm: String, provider: String): ByteArray {
        val signature = Signature.getInstance("algorithm", provider)
        signature.initSign(privateKey)
        signature.update(message.toByteArray());
        return signature.sign()
    }

    /**
     * sign using Elliptic Curve Digital Signature Algorithm
     */
    fun signWithECDSA(privateKey: PrivateKey, message: String): ByteArray {
        return sign(privateKey, message, "ECDSA", "BC")
    }


}