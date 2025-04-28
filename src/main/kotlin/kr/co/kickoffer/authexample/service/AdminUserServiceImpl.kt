package kr.co.kickoffer.authexample.service

import kr.co.kickoffer.authexample.model.entity.Admin
import kr.co.kickoffer.authexample.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminUserServiceImpl(
    private val adminRepository: AdminRepository
): AdminUserService {

    override fun create(email: String, password: String) {

        Admin(
            name = email,
            password = password,
            role = "ADMIN"

        )

        adminRepository.create(

        )
    }
}