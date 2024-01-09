package openway.com.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import openway.com.payload.user.LoginPayload
import openway.com.payload.user.RegisterPayload
import openway.com.services.AuthService
import openway.com.services.UserService

fun Application.authRoute(authService: AuthService){
    routing {
        route("/auth"){
            post("/register"){
                val params = call.receive<RegisterPayload>()
                val result = authService.registerUser(params)
                call.respond(result.statusCode, result)
            }
            post("/login"){
                val params = call.receive<LoginPayload>()
                val result = authService.login(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}