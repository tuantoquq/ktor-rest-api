package openway.com.repositories

import openway.com.models.User
import openway.com.payload.user.RegisterPayload

interface UserRepository {
    suspend fun create(params: RegisterPayload): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserById(id: Int): User?
}