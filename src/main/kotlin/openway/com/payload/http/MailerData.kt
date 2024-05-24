package openway.com.payload.http

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class From(val email: String)

@Serializable
data class To(val email: String)

@Serializable
data class Data(
    @SerialName("account_name")
    val accountName: String,

    @SerialName("support_email")
    val supportEmail: String,
)

@Serializable
data class Personalization(
    val email: String,
    val data: Data
)

@Serializable
data class SendMailRequest(
    val from: From,
    val to: List<To>,
    val personalization: List<Personalization>,
    @SerialName("template_id")
    val templateId: String
)