package bloklin
import bloklin.chain.Block
import bloklin.nodes.Node
import bloklin.nodes.NodesPool
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post



val nodesPool = NodesPool

fun Application.module() {
    install(DefaultHeaders)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("Bloklin server", ContentType.Application.Json)
        }
        get("/mine") {
            val block = Bloklin.mineBlock("data")
            call.respond(block)
        }
        get("/chain") {
            val chain = Bloklin.blockChain
            call.respond(chain)
        }
        get("/nodes/resolve") {
            Bloklin.consensus()
            call.respond(Bloklin.blockChain)
        }
        post("/nodes/register") {
            val jsonbody = call.receiveText()

            val nodeType = object : TypeToken<List<Node>>() {}.type
            val body = Gson().fromJson<List<Node>>(jsonbody, nodeType)
            if(body == null) call.respond(HttpStatusCode.fromValue(400), "provide a list of nodes")

            nodesPool.addNodes(body)
            call.respond(nodesPool.nodes)

        }
        //unsolicited block push
        post("/chain/block") {
            val jsonbody = call.receiveText()

            val body = Gson().fromJson<Block>(jsonbody, Block::class.java)
            if(body == null) call.respond(HttpStatusCode.fromValue(400), "provide a block")

            //todo impl

            call.respond(Bloklin.blockChain)

        }
    }
}