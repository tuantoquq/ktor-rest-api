package openway.com.services

import openway.com.payload.user.UserResponse
import openway.com.payload.http.BaseResponse
import openway.com.payload.user.LoginResponse

interface UserService {
    suspend fun profile(userId: String): BaseResponse<UserResponse>
    suspend fun refreshToken(userId: String): BaseResponse<LoginResponse>
}