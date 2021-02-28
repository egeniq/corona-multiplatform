package com.multiplatform.sample.shared.domain.model

/**
 * Created by Dima Kovalenko.
 */
data class CountryData(
    val country: String? = null,
    val totalCases: Long? = null,
    val totalDeaths: Long? = null,
    val newCases: Long? = null,
    val newDeaths: Long? = null
)
