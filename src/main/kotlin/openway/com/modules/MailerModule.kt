package openway.com.modules

import openway.com.services.MailerService
import openway.com.services.MailerServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val mailerModule = module {
    single { MailerServiceImpl() } bind MailerService::class
}