package kr.co.kickoffer.authexample.common

import kr.co.kickoffer.authexample.common.db.randomUUIDBytes
import kotlin.test.Test

class DBUtilsTests {

    @Test
    fun `UUID 생성 테스트`() {
        println(randomUUIDBytes().toString())
    }

}