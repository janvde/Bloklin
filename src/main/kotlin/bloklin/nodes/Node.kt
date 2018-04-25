package bloklin.nodes

import com.google.gson.annotations.SerializedName

data class Node(@SerializedName("ip") val ip: String, //ipv4 address
                @SerializedName("port") val port: Int // port number
)