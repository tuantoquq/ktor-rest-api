package openway.com.adapter

import openway.com.models.User
import openway.com.payload.user.UserResponse

class UserAdapter: EntityAdapter<User, UserResponse> {
    override fun toBusiness(entity: User): UserResponse {
        return UserResponse(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            role = entity.role,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            status = entity.status
        )
    }
}