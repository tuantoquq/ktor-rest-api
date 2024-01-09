package openway.com.repositories

import openway.com.configs.dbQuery
import openway.com.entities.UserTable
import openway.com.models.User
import openway.com.payload.user.RegisterPayload
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserRepositoryImpl: UserRepository {
    override suspend fun create(params: RegisterPayload): User? {
        var statement: InsertStatement<Number>?=null
        dbQuery {
            statement = UserTable.insert {
                it[name] = params.name
                it[email] = params.email
                it[password] = params.password
                it[role] = params.role
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun getUserByEmail(email: String): User? {
        var user: User? = null
        dbQuery {
            UserTable.select {
                UserTable.email.eq(email)
            }.map {
                user = rowToUser(it)
            }.singleOrNull()
        }
        return user
    }

    override suspend fun getUserById(id: Int): User? {
        var user: User? = null
        dbQuery {
            UserTable.select {
                UserTable.id.eq(id)
            }.map {
                user = rowToUser(it)
            }.singleOrNull()
        }
        return user
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) {
            null
        } else {
            User(
                id = row[UserTable.id],
                name = row[UserTable.name],
                email = row[UserTable.email],
                password = row[UserTable.password],
                role = row[UserTable.role],
                status = row[UserTable.status],
                createdAt = row[UserTable.createdAt].toString(),
                updatedAt = row[UserTable.updatedAt].toString()
            )
        }
    }
}