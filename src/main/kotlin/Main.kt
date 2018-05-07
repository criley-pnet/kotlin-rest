import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.event.Level
import service.setRoutes

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8080,
        watchPaths = listOf("kotlin-rest"),
        module = Application::mainModule
    ).start(wait = true)
}

fun Application.mainModule() {
    routing {
        setRoutes()
    }
    install(CallLogging) {
        level = Level.TRACE
    }
}
