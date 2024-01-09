package openway.com

import io.ktor.server.application.*
import openway.com.configs.DatabaseFactory
import openway.com.plugins.configureCORS
import openway.com.plugins.configureJWT
import openway.com.plugins.configureKoin
import openway.com.plugins.configureSerialize
import openway.com.routes.routes

fun Application.module() {
    // must keep this order: Koin -> Serialize -> JWT -> CORS -> Routes
    configureKoin()
    configureSerialize()
    configureJWT()
    configureCORS()
    routes()
    DatabaseFactory.init()
}