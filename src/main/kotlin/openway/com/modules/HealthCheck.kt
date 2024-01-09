package openway.com.modules

import openway.com.services.HealthCheckService
import openway.com.services.HealthCheckServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val healthCheckModule = module {
    single { HealthCheckServiceImpl() } bind HealthCheckService::class
}