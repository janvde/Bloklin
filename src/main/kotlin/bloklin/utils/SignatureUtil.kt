package bloklin.utils

import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature

object SignatureUtil {

    /**
     * sign a message with a given privatekey using a given algorithm and provider
     */
    private fun sign(privateKey: PrivateKey, message: String, algorithm: String, provider: String): ByteArray {
        val signature = Signature.getInstance(algorithm, provider)
        signature.initSign(privateKey)
        signature.update(message.toByteArray());
        return signature.sign()
    }

    /**
     * verify a signature using a given algorithm and provider
     */
    private fun verify(publicKey: PublicKey, data: String, signature: ByteArray, algorithm: String, provider: String): Boolean {
        val sig = Signature.getInstance(algorithm, provider)
        sig.initVerify(publicKey)
        sig.update(data.toByteArray());
        return sig.verify(signature)
    }

    /**
     * sign using Elliptic Curve Digital Signature Algorithm
     */
    fun signWithECDSA(privateKey: PrivateKey, message: String): ByteArray {
        return sign(privateKey, message, "ECDSA", "BC")
    }


    /**
     * verify a Elliptic Curve Digital Signature Algorithm signature
     */
    fun verifyECDSA(publicKey: PublicKey, data: String, signature: ByteArray): Boolean {
        return verify(publicKey, data, signature, "ECDSA", "BC")
    }


}