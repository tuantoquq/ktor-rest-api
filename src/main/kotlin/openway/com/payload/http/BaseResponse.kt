package openway.com.payload.http

import io.ktor.http.*

sealed class BaseResponse<T> (open val statusCode: HttpStatusCode = HttpStatusCode.OK){
    data class SuccessResponse<T>(val data: T? = null,
                                  val message: String? = "Successfully!",
                                  override val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)
    data class ErrorResponse<T>(val data: T? = null,
                                val message: String? = "Have an error occurred!",
                                override val statusCode: HttpStatusCode = HttpStatusCode.BadRequest,
    ) : BaseResponse<T>(statusCode)
}