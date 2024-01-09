package openway.com.modules

import openway.com.security.JwtManager
import openway.com.security.JwtManagerImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val jwtManagerModule = module {
    single { JwtManagerImpl() } bind JwtManager::class
}