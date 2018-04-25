
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

val blockChain = BlockChain

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
            val block = blockChain.mineBlock("data")
            call.respond(block)
        }
        get("/chain") {
            val chain = blockChain.chain
            call.respond(chain)
        }
    }
}