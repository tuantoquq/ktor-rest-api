package openway.com.modules

import openway.com.adapter.EntityAdapter
import openway.com.adapter.UserAdapter
import openway.com.repositories.UserRepository
import openway.com.repositories.UserRepositoryImpl
import openway.com.services.UserService
import openway.com.services.UserServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule = module {
    single { UserServiceImpl(get(), get(), get()) } bind UserService::class
    single { UserRepositoryImpl() } bind UserRepository::class
    factory { UserAdapter() } bind EntityAdapter::class
}