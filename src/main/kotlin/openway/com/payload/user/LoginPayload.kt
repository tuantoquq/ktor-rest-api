package openway.com.payload.user

data class LoginPayload(
    val email: String,
    val password: String,
)