package kr.co.kickoffer.authexample.service

import kr.co.kickoffer.authexample.model.dto.AdminUserDetails
import kr.co.kickoffer.authexample.repository.AdminRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class AdminUserDetailsService(
    private val adminRepository: AdminRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val adminUser = adminRepository.findByName(username ?: "")
            ?: throw UsernameNotFoundException("User not found")
        return AdminUserDetails(adminUser)
    }
}