package bloklin.remote

import bloklin.Block
import bloklin.nodes.Node
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NodeService {
    @GET("/chain")
    fun getChain(): Observable<List<Block>>

    @POST("/nodes/register")
    fun registerNodes(@Body nodes: List<Node>): Observable<List<Node>>
}