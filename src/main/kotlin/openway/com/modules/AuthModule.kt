package openway.com.modules

import openway.com.services.AuthService
import openway.com.services.AuthServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    single { AuthServiceImpl(get(), get(), get()) } bind AuthService::class
}