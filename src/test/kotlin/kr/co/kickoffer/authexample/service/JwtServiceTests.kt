package kr.co.kickoffer.authexample.service

import kr.co.kickoffer.authexample.model.dto.JwtClaimDto
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test

class JwtServiceTests {


    private val jwtService = JwtServiceImpl()

    @Test
    fun `토큰 생성 테스트`() {

        val dto = JwtClaimDto(
            id = "testId",
            email = "test@aladin.co.kr",
            exp = System.currentTimeMillis() + 1000 * 60,
            iat = System.currentTimeMillis()
        )

        val token = jwtService.create(dto)

        println(token)
    }

    @Test
    fun `토큰 검증 테스트`() {
        jwtService.validate("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6InRlc3RJZCIsImVtYWlsIjoidGVzdEBhbGFkaW4uY28ua3IiLCJleHAiOjE3NDUyMjM2NTUsImlhdCI6MTc0NTIyMzU5NTIyMn0.nmIfdYD8G9QLTNyGqSbepD9RGdYWU4jiYLHz3Av_uyA")
    }

}