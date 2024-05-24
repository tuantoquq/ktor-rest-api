package openway.com.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import openway.com.services.MailerService

fun Application.testMailerRoute(mailerService: MailerService){
    routing {
        get("/test-mailer"){
            val result = mailerService.sendEmail(
                templateId = "yzkq340j3wkld796",
                to = "recipient@email.com"
            )
            call.respond(result.statusCode, result)
        }
    }

}