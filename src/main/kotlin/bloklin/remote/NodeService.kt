package bloklin.remote

import bloklin.chain.BlockChain
import bloklin.nodes.Node
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NodeService {
    @GET("/chain")
    fun getChain(): Single<BlockChain>

    @POST("/nodes/register")
    fun registerNodes(@Body nodes: List<Node>): Single<List<Node>>
}