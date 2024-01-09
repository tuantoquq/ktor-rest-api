package openway.com

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testHealthCheck() = testApplication {
        client.get("/health-check").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
