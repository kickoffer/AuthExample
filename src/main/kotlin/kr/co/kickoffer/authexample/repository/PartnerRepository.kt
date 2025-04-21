package kr.co.kickoffer.authexample.repository

import kr.co.kickoffer.authexample.model.entity.Partner

interface PartnerRepository {
    fun findByName(name: String): Partner?
}