package openway.com.services

import openway.com.payload.http.BaseResponse

interface MailerService {
   suspend fun sendEmail(to: String, templateId: String): BaseResponse<String>
}