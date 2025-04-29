package kr.co.kickoffer.authexample.service

import kr.co.kickoffer.authexample.model.dto.CreateAdminDto
import kr.co.kickoffer.authexample.model.entity.Admin
import kr.co.kickoffer.authexample.repository.AdminRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AdminUserServiceImpl(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder
): AdminUserService {

    override fun create(email: String, password: String) {
        adminRepository.create(
            CreateAdminDto(
                name = email,
                password = passwordEncoder.encode(password),
                role = "ADMIN"
            )
        )
    }
}