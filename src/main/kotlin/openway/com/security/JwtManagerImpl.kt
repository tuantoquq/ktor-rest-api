package openway.com.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import openway.com.cache.RedisClient
import openway.com.configs.Environments
import openway.com.constants.USER_AUTH_SESSION_PREFIX
import openway.com.payload.jwt.ClaimsGenerator
import openway.com.payload.jwt.JwtGeneratorOptions
import openway.com.payload.jwt.JwtPayload
import java.util.*
import kotlin.collections.HashMap

class JwtManagerImpl: JwtManager {
    override fun generateToken(claims: ClaimsGenerator, secretKey: String, options: JwtGeneratorOptions?): String {
        val algorithm = Algorithm.HMAC256(secretKey)
        val now = Date()
        val exp = options?.expireInMinutes?.let { Date(now.time + it.toLong() * 60 * 1000) }
            ?: Date(now.time + 60 * 1000 * 1000)
        return JWT.create()
            .withIssuedAt(now)
            .withExpiresAt(exp)
            .withSubject(claims.id.toString())
            .withClaim("name", claims.name)
            .withIssuer(Environments.jwtIssuer)
            .sign(algorithm)
    }


    override fun getVerifiedClaims(token: String): JwtPayload {
        val jwt = JWT.decode(token)
        return JwtPayload(
            jwt.subject,
            jwt.expiresAt.time,
            jwt.issuedAt.time,
            jwt.issuer,
        )
    }

    override fun getVerifier(secretKey: String): JWTVerifier {
        return JWT.require(getAlgorithm(secretKey))
            .withIssuer(Environments.jwtIssuer)
            .build()
    }

    override fun getAlgorithm(secretKey: String?): Algorithm {
        return Algorithm.HMAC256(secretKey)
    }

    override fun generateTokenPair(claims: ClaimsGenerator): Pair<String, String> {
        val redisClient = RedisClient.getInstance()
        val accessToken = generateToken(
            ClaimsGenerator(id = claims.id, name = claims.name),
            Environments.jwtAccessSecretKey,
            JwtGeneratorOptions(expireInMinutes = Environments.jwtAccessExpireInMinutes)
        )
        val refreshToken = generateToken(
            ClaimsGenerator(id = claims.id, name = claims.name),
            Environments.jwtRefreshSecretKey,
            JwtGeneratorOptions(expireInMinutes = Environments.jwtRefreshExpireInMinutes)
        )
        val mapAuthData: HashMap<String, String> = HashMap<String, String>()
        mapAuthData["access"] = accessToken.split(".")[1]
        mapAuthData["refresh"] = refreshToken.split(".")[1]
        redisClient.hset("${USER_AUTH_SESSION_PREFIX}${claims.id}", mapAuthData)
        return Pair(accessToken, refreshToken)
    }
}