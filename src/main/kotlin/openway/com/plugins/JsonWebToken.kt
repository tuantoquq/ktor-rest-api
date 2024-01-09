package openway.com.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import openway.com.cache.RedisClient
import openway.com.configs.Environments
import openway.com.constants.USER_AUTH_SESSION_PREFIX
import openway.com.security.JwtManager
import org.koin.ktor.ext.inject

fun Application.configureJWT() {
    val jwtManager: JwtManager by inject<JwtManager>()
    val redisClient = RedisClient.getInstance()
    install(Authentication){
        jwt("auth-access") {
            verifier(jwtManager.getVerifier(Environments.jwtAccessSecretKey))
            realm = Environments.jwtIssuer
            validate { credential ->
                val userIdStr = credential.payload.subject
                UserIdPrincipal(userIdStr)
            }
        }
        jwt ("auth-refresh"){
            verifier(jwtManager.getVerifier(Environments.jwtRefreshSecretKey))
            realm = Environments.jwtIssuer
            validate {credential ->
                val userIdStr = credential.payload.subject
                val token = request.headers["Authorization"]?.removePrefix("Bearer ")
                val refreshPrefix = redisClient.hget(USER_AUTH_SESSION_PREFIX + userIdStr, "refresh")
                if(refreshPrefix != token!!.split(".")[1]) return@validate null
                UserIdPrincipal(userIdStr)
            }
        }
    }
}