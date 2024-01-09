package openway.com.payload.jwt

data class JwtPayload (
    val sub: String,
    val exp: Long,
    val iat: Long,
    val iss: String,
)