package kr.co.kickoffer.authexample.model.entity

import java.time.LocalDateTime
import java.util.UUID

data class Admin(
    val id: Int,
    val uuid: UUID,
    val name: String,
    val password: String,
    val role: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val lastLoginAt: LocalDateTime?
)