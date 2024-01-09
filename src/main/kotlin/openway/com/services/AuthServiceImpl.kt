package openway.com.services

import io.ktor.http.*
import openway.com.adapter.UserAdapter
import openway.com.payload.jwt.ClaimsGenerator
import openway.com.payload.user.LoginPayload
import openway.com.payload.user.LoginResponse
import openway.com.payload.user.RegisterPayload
import openway.com.repositories.UserRepository
import openway.com.security.JwtManager
import openway.com.security.hashPassword
import openway.com.security.verifyPassword
import openway.com.payload.http.BaseResponse

class AuthServiceImpl(private val userRepository: UserRepository,
                      private val userAdapter: UserAdapter,
                      private val jwtManager: JwtManager,): AuthService {
    /**
     * Register user for customer
     * @param params RegisterPayload (email, password, name)
     * @return BaseResponse<String?> -> SuccessResponse<String?> or ErrorResponse<String?>
     * @author tuannha
     */
    override suspend fun registerUser(params: RegisterPayload): BaseResponse<String?> {
        val existUser = userRepository.getUserByEmail(params.email)
        if(existUser != null){
            return BaseResponse.ErrorResponse(message = "User already exists", statusCode = HttpStatusCode.BadRequest)
        }
        params.password = hashPassword(params.password)
        val user = userRepository.create(params)
        return if(user != null){
            BaseResponse.SuccessResponse(message = "Register successfully")
        } else {
            BaseResponse.ErrorResponse(message = "User already exists", statusCode = HttpStatusCode.BadRequest)
        }
    }

    /**
     * Login user for customer
     * @param params LoginPayload (email, password)
     * @return BaseResponse<LoginResponse?> -> SuccessResponse<LoginResponse?> or ErrorResponse<LoginResponse?>
     * @author tuannha
     */
    override suspend fun login(params: LoginPayload): BaseResponse<LoginResponse?> {
        val user = userRepository.getUserByEmail(params.email)
        return if(user != null){
            if(!verifyPassword(params.password, user.password)){
                return BaseResponse.ErrorResponse(message = "Invalid credentials", statusCode = HttpStatusCode.BadRequest)
            }
            val (accessToken, refreshToken) = jwtManager.generateTokenPair(ClaimsGenerator(id = user.id, name = user.name))
            BaseResponse.SuccessResponse(data = LoginResponse(accessToken = accessToken, refreshToken = refreshToken))
        } else {
            BaseResponse.ErrorResponse(message = "User not found", statusCode = HttpStatusCode.NotFound)
        }
    }

}