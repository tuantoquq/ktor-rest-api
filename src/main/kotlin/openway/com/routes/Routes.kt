package openway.com.routes

import io.ktor.server.application.*
import org.koin.ktor.ext.get

fun Application.routes() {
    healthCheckRoute(get())
    authRoute(get())
    userRoutes(get())
    openapiRoute()
    testMailerRoute(get())
}