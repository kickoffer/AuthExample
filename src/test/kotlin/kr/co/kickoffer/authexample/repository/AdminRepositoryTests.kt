package kr.co.kickoffer.authexample.repository

import kr.co.kickoffer.authexample.model.dto.CreateAdminDto
import kr.co.kickoffer.authexample.model.table.AdminTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import kotlin.test.Test

@Testcontainers
class AdminRepositoryTests {

    @Container
    val db = MySQLContainer(DockerImageName.parse("mysql:latest"))
        .withExposedPorts(3306)
        .withUsername("root")
        .withPassword("inb5040sr12#\$%")
        .withDatabaseName("test")


    private lateinit var repo: AdminRepository

    @BeforeEach
    fun setUp() {
        Database.connect(
            url = db.jdbcUrl,
            driver = "com.mysql.cj.jdbc.Driver",
            user = db.username,
            password = db.password
        )

        transaction {
            SchemaUtils.create(AdminTable)
        }

        repo = AdminRepositoryImpl()
    }

    @Test
    fun `Admin 생성 테스트`() {
        repo.create(
            CreateAdminDto(
                name = "testAdmin",
                password = "testPassword",
                role = "ADMIN"
            )
        )

        println(repo.findByName("testAdmin"))
    }

//    @AfterEach
//    fun tearDown() {
//        db.stop()
//    }

}