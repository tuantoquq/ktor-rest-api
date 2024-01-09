package openway.com.configs

import com.typesafe.config.ConfigFactory

// This method is called via reflection by the fully qualified name of the module in the application.conf file
@Suppress("UNUSED")
object Environments {
    private val envConfig = ConfigFactory.load()

    // application
    val port: Int = envConfig.getInt("ktor.deployment.port")

    // jwt configurations
    val jwtIssuer: String = envConfig.getString("jwt.issuer")
    val jwtAccessSecretKey: String = envConfig.getString("jwt.access.secret")
    val jwtRefreshSecretKey: String = envConfig.getString("jwt.refresh.secret")
    val jwtAccessExpireInMinutes: Int = envConfig.getInt("jwt.access.expireInMinutes")
    val jwtRefreshExpireInMinutes: Int = envConfig.getInt("jwt.refresh.expireInMinutes")

    // database configurations
    val dbUser: String = envConfig.getString("database.username")
    val dbPass: String = envConfig.getString("database.password")
    val dbHost: String = envConfig.getString("database.host")
    val dbPort: String = envConfig.getString("database.port")
    val dbName: String = envConfig.getString("database.db")
    val dbDriver: String = envConfig.getString("database.driver")

    // redis configurations
    val redisHost: String = envConfig.getString("redis.host")
    val redisPort: Int = envConfig.getInt("redis.port")
}