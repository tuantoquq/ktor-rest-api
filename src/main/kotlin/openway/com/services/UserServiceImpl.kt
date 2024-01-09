package openway.com.services

import io.ktor.http.*
import openway.com.adapter.UserAdapter
import openway.com.models.User
import openway.com.payload.user.UserResponse
import openway.com.repositories.UserRepository
import openway.com.payload.http.BaseResponse
import openway.com.payload.jwt.ClaimsGenerator
import openway.com.payload.user.LoginResponse
import openway.com.security.JwtManager


class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userAdapter: UserAdapter,
    private val jwtManager: JwtManager,
) : UserService {
    override suspend fun profile(userId: String): BaseResponse<UserResponse> {
        val user: User? = userRepository.getUserById(userId.toInt())
        return if (user != null) {
            BaseResponse.SuccessResponse(data = userAdapter.toBusiness(user))
        } else {
            BaseResponse.ErrorResponse(message = "User not found")
        }
    }

    override suspend fun refreshToken(userId: String): BaseResponse<LoginResponse> {
        val user = userRepository.getUserById(userId.toInt())
        return if(user != null){
            val (accessToken, refreshToken) = jwtManager.generateTokenPair(ClaimsGenerator(id = user.id, name = user.name))
            BaseResponse.SuccessResponse(data = LoginResponse(accessToken = accessToken, refreshToken = refreshToken))
        } else {
            BaseResponse.ErrorResponse(message = "User not found", statusCode = HttpStatusCode.NotFound)
        }
    }
}