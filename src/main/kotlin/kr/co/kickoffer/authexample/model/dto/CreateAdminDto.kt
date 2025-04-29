package kr.co.kickoffer.authexample.model.dto

import io.jsonwebtoken.security.Password

data class CreateAdminDto(
    val name: String,
    val password: String,
    val role: String
)
