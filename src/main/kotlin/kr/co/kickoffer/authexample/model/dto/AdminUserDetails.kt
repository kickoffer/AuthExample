package kr.co.kickoffer.authexample.model.dto

import kr.co.kickoffer.authexample.model.entity.Admin
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class AdminUserDetails(
    private val admin: Admin
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList("ROLE_${admin.role}")
    }

    override fun getPassword(): String {
        return admin.password
    }

    override fun getUsername(): String {
        return admin.name
    }
}