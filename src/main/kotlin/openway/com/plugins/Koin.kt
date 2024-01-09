package openway.com.plugins

import io.ktor.server.application.*
import openway.com.modules.authModule
import openway.com.modules.healthCheckModule
import openway.com.modules.jwtManagerModule
import openway.com.modules.userModule
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(authModule, healthCheckModule, jwtManagerModule, userModule)
    }
}