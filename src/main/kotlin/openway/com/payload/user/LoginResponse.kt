package openway.com.payload.user

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)
