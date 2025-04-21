package kr.co.kickoffer.authexample.model.dto

data class JwtClaimDto(
    val id: String,
    val email: String,
    val iat: Long
)


fun JwtClaimDto.toClaims(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "email" to email,
        "iat" to iat
    )
}
