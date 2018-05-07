package service

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.setRoutes() {
    route("/hello") {
        get {
            call.respondText("Hello World!", ContentType.Text.Plain)
        }
    }
    route("/api") {
        route("roll/{dice}") {
            get {
                val command: String = call.parameters["dice"] ?: ""
                val result = parseCommand(command)

                call.respondText("Rolling $command: $result", ContentType.Text.Plain)
            }
        }
    }
    get("/error") {
        throw RuntimeException("Server Error: Test Error Message")
    }
}
