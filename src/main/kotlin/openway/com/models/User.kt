package openway.com.models

import openway.com.constants.UserRole
import openway.com.constants.UserStatus
import java.time.LocalDateTime

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val role: UserRole,
    val status: UserStatus,
    val createdAt: String,
    val updatedAt: String
)
