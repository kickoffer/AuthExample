package kr.co.kickoffer.authexample.common

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import kotlin.test.BeforeTest
import kotlin.test.Test


class SecurityContextHolderTests {

    private fun setMockAuthentication(principal: String, credential: String, role: String) {
        val context = SecurityContextHolder.createEmptyContext()
        val mockAuthentication = TestingAuthenticationToken(principal, credential, role)

        context.authentication = mockAuthentication
        SecurityContextHolder.setContext(context)
    }


    @BeforeTest
    fun setup() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL)
        setMockAuthentication("admin", "password", "ROLE_ADMIN")
    }

    @Test
    fun `SecurityContextHolder 에 현재 인증된 사용자 정보를 셋팅한다`() {
        val authentication: Authentication = SecurityContextHolder.getContext().getAuthentication()

        assertThat(authentication.isAuthenticated()).isTrue()
        assertThat(authentication.getName()).isEqualTo("admin")
        assertThat(authentication.getCredentials()).isEqualTo("password")
        assertThat(authentication.getAuthorities())
            .extracting({ obj: GrantedAuthority? -> obj!!.getAuthority() })
            .contains(Tuple("ROLE_ADMIN"))
    }



}