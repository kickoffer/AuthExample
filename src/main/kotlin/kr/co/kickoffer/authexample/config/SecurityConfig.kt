package kr.co.kickoffer.authexample.config

import kr.co.kickoffer.authexample.common.filter.JwtAuthenticationFilter
import kr.co.kickoffer.authexample.service.AdminAuthenticationProvider
import kr.co.kickoffer.authexample.service.AdminUserDetailsService
import kr.co.kickoffer.authexample.service.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val adminUserDetailsService: AdminUserDetailsService,
//    private val authenticationProvider: AdminAuthenticationProvider,
    private val jwtService: JwtService
) {

    @Bean
    fun jwtAuthFilter(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(jwtService, adminUserDetailsService)
    }


    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        return CorsConfigurationSource { request ->
            val config = org.springframework.web.cors.CorsConfiguration()
            config.allowedOrigins = listOf("http://localhost:3000")
            config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
            config.allowedHeaders = listOf("*")
            config.allowCredentials = true
            config
        }
    }


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
        .csrf { csrf -> csrf.disable() }
        .cors { cors -> cors.configurationSource(corsConfigurationSource()) }
        .authorizeHttpRequests { auth ->
            // Allow Public Access
            auth.requestMatchers("/css/**").permitAll()
            auth.requestMatchers("/js/**").permitAll()
            auth.requestMatchers("/api/auth/**").permitAll()

            // Allow Authenticated Access
//            auth.requestMatchers("/admin/**")
//                .hasAnyRole("ADMIN")
//                .anyRequest()
//                .authenticated()
        }
        .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .formLogin { form -> form.disable() }
        .httpBasic { http -> http.disable() }
        .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter::class.java)
//        .authenticationProvider(authenticationProvider)
        .userDetailsService(adminUserDetailsService)
        .build()

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}