package openway.com.services

import openway.com.payload.user.LoginPayload
import openway.com.payload.user.LoginResponse
import openway.com.payload.user.RegisterPayload
import openway.com.payload.http.BaseResponse

interface AuthService {
    suspend fun login(params: LoginPayload): BaseResponse<LoginResponse?>
    suspend fun registerUser(params: RegisterPayload): BaseResponse<String?>
}