package openway.com.configs

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import openway.com.entities.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

private fun hikari() :HikariDataSource{
    val config = HikariConfig()
    val envConfig = ConfigFactory.load()
    val dbUrl = "jdbc:mysql://${Environments.dbUser}:${Environments.dbPass}@${Environments.dbHost}:${Environments.dbPort}" +
            "/${Environments.dbName}?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC"

    config.driverClassName = Environments.dbDriver
    config.jdbcUrl = dbUrl
    config.validate()
    return HikariDataSource(config)
}

public suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction {
        block()
    }
}

object DatabaseFactory {
    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(UserTable);
        }
    }
}