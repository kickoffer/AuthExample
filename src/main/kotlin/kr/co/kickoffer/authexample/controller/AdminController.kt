package kr.co.kickoffer.authexample.controller

import kr.co.kickoffer.authexample.service.AdminUserDetailsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/admin")
@RestController
class AdminController(
    private val adminUserDetailsService: AdminUserDetailsService
) {

    @GetMapping("/health")
    fun health():  Int {
        return 200
    }

    @PostMapping("/sign-in")
    fun signIn(): String {

        return "Admin Sign In"
    }

    @PostMapping("/sign-out")
    fun signOut(): String {
        return "Admin Sign Out"
    }

}