package kr.co.kickoffer.authexample.service

import kr.co.kickoffer.authexample.model.dto.CreateAdminDto
import kr.co.kickoffer.authexample.model.entity.Admin
import kr.co.kickoffer.authexample.repository.AdminRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID
import javax.security.auth.login.CredentialException

@Service
class AdminUserServiceImpl(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder
): AdminUserService {

    override fun create(email: String, password: String) = transaction {

        adminRepository.findByName(email)?.let {
            throw CredentialException("User already exists")
        }

        adminRepository.create(
            CreateAdminDto(
                name = email,
                password = passwordEncoder.encode(password),
                role = "ADMIN"
            )
        )
    }
}