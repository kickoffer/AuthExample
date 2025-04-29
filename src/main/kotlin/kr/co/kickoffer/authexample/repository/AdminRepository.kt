package kr.co.kickoffer.authexample.repository

import kr.co.kickoffer.authexample.model.dto.CreateAdminDto
import kr.co.kickoffer.authexample.model.entity.Admin

interface AdminRepository {
    fun findByName(name: String): Admin?
    fun create(admin: CreateAdminDto)
}