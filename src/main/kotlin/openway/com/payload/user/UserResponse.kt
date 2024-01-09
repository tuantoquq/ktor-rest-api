package openway.com.payload.user

import openway.com.constants.UserRole
import openway.com.constants.UserStatus

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String,
    val role: UserRole,
    val status: UserStatus,
    val createdAt: String,
    val updatedAt: String
)

