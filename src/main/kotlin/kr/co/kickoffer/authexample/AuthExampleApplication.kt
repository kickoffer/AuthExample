package kr.co.kickoffer.authexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class AuthExampleApplication

fun main(args: Array<String>) {
    runApplication<AuthExampleApplication>(*args)
}
