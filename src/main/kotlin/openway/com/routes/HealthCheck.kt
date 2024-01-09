package openway.com.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import openway.com.services.HealthCheckService

fun Application.healthCheckRoute(healthCheck: HealthCheckService){
    routing {
            get("/health-check"){
                val result = healthCheck.getHealthCheck()
                call.respond(result.statusCode, result)
            }
    }
}