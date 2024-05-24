package openway.com.plugins

import io.ktor.server.application.*
import openway.com.modules.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(authModule, healthCheckModule, jwtManagerModule, userModule, mailerModule)
    }
}