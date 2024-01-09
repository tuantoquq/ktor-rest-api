package openway.com.payload.user

import openway.com.constants.UserRole

data class RegisterPayload(
    val name: String,
    val email: String,
    var password: String,
    val role: UserRole,
)
