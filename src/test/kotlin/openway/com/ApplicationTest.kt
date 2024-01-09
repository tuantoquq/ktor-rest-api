package openway.com

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import openway.com.adapter.UserAdapter
import openway.com.configs.DatabaseFactory
import kotlin.test.*
import openway.com.repositories.UserRepository
import openway.com.repositories.UserRepositoryImpl
import openway.com.routes.authRoute
import openway.com.services.UserService
import openway.com.services.UserServiceImpl

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            install(ContentNegotiation)
            {
                jackson()
            }
            DatabaseFactory.init()
            val userRepository: UserRepository = UserRepositoryImpl()
            val userAdapter: UserAdapter = UserAdapter()
            val userService: UserService = UserServiceImpl(userRepository, userAdapter)
            authRoute(userService)

        }
        client.get("/health-check").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Service is healthy!", bodyAsText())
        }
    }
}
