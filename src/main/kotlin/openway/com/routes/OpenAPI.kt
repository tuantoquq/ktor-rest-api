package openway.com.routes

import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.routing.*

fun Application.openapiRoute() {
   routing {
       openAPI(path = "openapi", swaggerFile = "openapi/documentation.yaml")
   }
}