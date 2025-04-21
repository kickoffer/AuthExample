package kr.co.kickoffer.authexample.model.entity

data class Partner(
    val id: Int,
    val uuid: String,
    val name: String,
    val password: String,
    val role: String,
    val createdAt: String,
    val lastLoginAt: String?
)