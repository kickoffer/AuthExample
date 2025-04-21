package kr.co.kickoffer.authexample.service

import kr.co.kickoffer.authexample.model.dto.JwtClaimDto

interface JwtService {
    fun create(dto: JwtClaimDto): String
    fun validate(token: String)
    fun extract(token: String): JwtClaimDto
}