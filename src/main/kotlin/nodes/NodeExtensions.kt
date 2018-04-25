package nodes

import java.util.regex.Pattern


/**
 * check if the ipv4 address is valid
 */
fun Node.isValidAddress(): Boolean {
    val p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    val m = p.matcher(this.ip)
    return m.find()

}

/**
 * check if the port is within port range
 */
fun Node.isValidPort(): Boolean {
    return (this.port in 0..65535)
}