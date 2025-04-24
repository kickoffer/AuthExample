package kr.co.kickoffer.authexample.model.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@ConfigurationProperties(prefix = "spring.security.jwt")
data class JwtProperties(
    val secretKey: String,
    val tokenValidityInMilliseconds: Long,
)
