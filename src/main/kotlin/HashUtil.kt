import java.security.MessageDigest

object HashUtil {
    /*fun sha256(text: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val digest = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
        return digest.joinToString(separator = "", transform = { a -> String(charArrayOf(HEX_CHARS[a.toInt() shr 4 and 0x0f], HEX_CHARS[a.toInt() and 0x0f])) })
    }*/

    fun sha256(input: String) = hashString("SHA-256", input)

    fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }
}