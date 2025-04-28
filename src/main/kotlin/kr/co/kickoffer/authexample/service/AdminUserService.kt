package kr.co.kickoffer.authexample.service

interface AdminUserService {
    fun create(email: String, password: String)
}