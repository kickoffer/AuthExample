package kr.co.kickoffer.authexample.controller

import kr.co.kickoffer.authexample.model.dto.JwtClaimDto
import kr.co.kickoffer.authexample.service.AdminAuthenticationProvider
import kr.co.kickoffer.authexample.service.JwtService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant


data class LoginRequest(val username: String, val password: String)

@RequestMapping("/api/auth")
@RestController
class AuthController(
    private val authenticationProvider: AdminAuthenticationProvider,
    private val jwtService: JwtService
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/admin/sign-in")
    fun signIn(@RequestBody request: LoginRequest): String {

        logger.info("Sign In")

        val authentication = authenticationProvider.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )

        val userDetails = authentication.principal as org.springframework.security.core.userdetails.UserDetails

        val jwtClaimDto = JwtClaimDto(
            id = userDetails.username,
            email = userDetails.username,
            iat = Instant.now().epochSecond
        )

        return jwtService.create(jwtClaimDto)
    }

    @PostMapping("/admin/sign-out")
    fun signOut(): String {
        return "Admin Sign Out"
    }

    @PostMapping("/partner/sign-in")
    fun partnerSignIn(): String {
        return "Partner Sign In"
    }

    @PostMapping("/partner/sign-out")
    fun partnerSignOut(): String {
        return "Partner Sign Out"
    }


}