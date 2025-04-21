package kr.co.kickoffer.authexample.model.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object PartnerTable : Table("partner") {
    val id = integer("id").autoIncrement()
    val uuid = varchar("uuid", 36)
    val name = varchar("name", 50)
    val password = varchar("password", 255)
    val role = varchar("role", 20)
    val createdAt = datetime("created_at")
    val lastLoginAt = datetime("last_login_at").nullable()

    override val primaryKey = PrimaryKey(id)
}