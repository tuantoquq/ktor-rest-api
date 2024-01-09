package openway.com.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import openway.com.services.UserService

fun Application.userRoutes(userService: UserService) {
    routing {
        route("/user") {
            authenticate("auth-access") {
                get("/profile") {
                    val userId = call.principal<UserIdPrincipal>()?.name
                    val result = userService.profile(userId!!)
                    call.respond(result.statusCode, result)
                }
            }
            authenticate("auth-refresh") {
                post("/refresh-token") {
                    val userId = call.principal<UserIdPrincipal>()?.name
                    val result = userService.refreshToken(userId!!)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}