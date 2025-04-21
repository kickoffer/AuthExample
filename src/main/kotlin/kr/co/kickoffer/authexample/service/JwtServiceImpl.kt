package kr.co.kickoffer.authexample.service

import io.jsonwebtoken.security.Keys
import kr.co.kickoffer.authexample.model.dto.JwtClaimDto
import org.springframework.stereotype.Service
import java.util.Base64
import java.util.Date

import io.jsonwebtoken.Jwts.*
import kr.co.kickoffer.authexample.model.dto.toClaims
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class JwtServiceImpl: JwtService {

    private final val secretKey = "SpringSecurityKey_P@ssword_http://Spring.io"
    private final val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    override fun create(dto: JwtClaimDto): String {
        val expireAt = LocalDateTime.now().plusMinutes(1)
        val exp = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant())
        return builder()
            .signWith(key, SIG.HS256)
            .claims(dto.toClaims())
            .expiration(exp)
            .compact()
    }

    override fun validate(token: String) {

        try {
            val payload = extract(token)
            println(payload)
        } catch (e: Exception) {
            if (e is io.jsonwebtoken.security.SignatureException) {
                throw RuntimeException("Invalid signature")
            } else if (e is io.jsonwebtoken.ExpiredJwtException) {
                throw RuntimeException("Token expired")
            } else {
                throw RuntimeException("JTW Exception")
            }

        }

    }

    override fun extract(token: String): JwtClaimDto {
        val parser = parser()
            .verifyWith(key)
            .build()

        val result = parser.parseSignedClaims(token)
        val claims = result.payload

        return JwtClaimDto(
            id = claims["id"] as String,
            email = claims["email"] as String,
            iat = (claims["iat"] as Number).toLong()
        )
    }
}