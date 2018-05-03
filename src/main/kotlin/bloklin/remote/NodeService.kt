package bloklin.remote

import bloklin.chain.Block
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

    @GET("/nodes/resolve")
    fun resolveNodes(): Single<Void>

    @POST("/chain/block")
    fun pushBlock(@Body block: Block): Single<Void>
}