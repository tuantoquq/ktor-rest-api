package openway.com.services

import openway.com.payload.http.BaseResponse

class HealthCheckServiceImpl: HealthCheckService{
    override suspend fun getHealthCheck(): BaseResponse<String> {
        return BaseResponse.SuccessResponse(data = "Service is healthy!")
    }
}