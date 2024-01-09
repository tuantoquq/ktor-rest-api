package openway.com.plugins

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialize() {
    install(ContentNegotiation) {
        jackson ()
    }
}