package kr.co.kickoffer.authexample.model.table

import kr.co.kickoffer.authexample.common.db.randomUUIDBytes
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object AdminTable : Table("admin") {
    val id = integer("id").autoIncrement()
    val uuid = binary("uuid", length = 16).clientDefault { randomUUIDBytes() }
    val name = varchar("name", 50)
    val password = char("password", 60)
    val role = varchar("role", 20)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val lastLoginAt = datetime("last_login_at").nullable()

    override val primaryKey = PrimaryKey(id) // name is optional here
}