import java.security.MessageDigest

object HashUtil {
    fun sha256(text: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val digest = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
        return digest.joinToString(separator = "", transform = { a -> String(charArrayOf(HEX_CHARS[a.toInt() shr 4 and 0x0f], HEX_CHARS[a.toInt() and 0x0f])) })
    }
}