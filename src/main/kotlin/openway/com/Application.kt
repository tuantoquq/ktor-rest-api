package openway.com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import openway.com.configs.Environments

fun main() {
    embeddedServer(Netty,
        port = Environments.port,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}


