package openway.com.services

import openway.com.payload.http.BaseResponse

interface HealthCheckService {
    suspend fun getHealthCheck(): BaseResponse<String>
}