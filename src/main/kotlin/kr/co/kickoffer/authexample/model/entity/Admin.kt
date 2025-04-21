package kr.co.kickoffer.authexample.model.entity

import java.time.LocalDateTime

data class Admin(
    val id: Int,
    val uuid: String,
    val name: String,
    val password: String,
    val role: String,
    val createdAt: LocalDateTime,
    val lastLoginAt: LocalDateTime?
)