package kr.co.kickoffer.authexample.repository

import kr.co.kickoffer.authexample.common.db.randomUUIDBytes
import kr.co.kickoffer.authexample.common.db.toUUID
import kr.co.kickoffer.authexample.model.dto.CreateAdminDto
import kr.co.kickoffer.authexample.model.entity.Admin
import kr.co.kickoffer.authexample.model.table.AdminTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.sql.SQLException

@Repository
class AdminRepositoryImpl : AdminRepository {
    override fun findByName(name: String): Admin? = transaction {
        AdminTable
            .selectAll()
            .where { AdminTable.name eq name }
            .map {
                Admin(
                    id = it[AdminTable.id],
                    uuid = it[AdminTable.uuid].toUUID(),
                    name = it[AdminTable.name],
                    password = it[AdminTable.password],
                    role = it[AdminTable.role],
                    createdAt = it[AdminTable.createdAt],
                    lastLoginAt = it[AdminTable.lastLoginAt]
                )
            }
            .firstOrNull()
    }

    override fun create(admin: CreateAdminDto): Unit = transaction {
        AdminTable.insert {
            it[name] = admin.name
            it[password] = admin.password
            it[role] = admin.role
        }
        .resultedValues?.firstOrNull()
            ?: throw SQLException("Failed to insert admin")
    }
}