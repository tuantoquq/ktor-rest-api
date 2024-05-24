package openway.com.services

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import openway.com.configs.Environments
import openway.com.payload.http.*


class MailerServiceImpl : MailerService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }
    override suspend fun sendEmail(to: String, templateId: String): BaseResponse<String> {
        val requestBody = SendMailRequest(
            from = From(email = "info@domain.com"),
            to = listOf(To(email = to)),
            personalization = listOf(
                Personalization(
                    email = to,
                    data = Data(accountName = "tuannha", supportEmail = "tuannha@support.com")
                )
            ),
            templateId = templateId
        )

        try {
            val response: HttpResponse = client.post("https://api.mailersend.com/v1/email") {
                contentType(ContentType.Application.Json)
                header("X-Requested-With", "XMLHttpRequest")
                bearerAuth(Environments.mailerApiKey)
                setBody(requestBody)
            }
            println("Response: ${response.bodyAsText()}")
            if (response.status != HttpStatusCode.OK) {
                return BaseResponse.ErrorResponse(message = "Failed to send email", statusCode = response.status)
            }
            return BaseResponse.SuccessResponse(data = "Email sent successfully")
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return BaseResponse.ErrorResponse(message = "Failed to send email", statusCode = HttpStatusCode.InternalServerError)
        } finally {
            client.close()
        }
    }
}