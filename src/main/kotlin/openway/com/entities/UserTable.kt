package openway.com.entities

import openway.com.constants.UserRole
import openway.com.constants.UserStatus
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable: Table("user") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val email = varchar("email", 100)
    val password = varchar("password", 100)
    var role = enumeration("role", UserRole::class).clientDefault { UserRole.USER }
    var status = enumeration("status", UserStatus::class).clientDefault { UserStatus.ACTIVE }
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(id)
}