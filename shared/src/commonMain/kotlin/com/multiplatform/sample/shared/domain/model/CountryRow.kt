package com.multiplatform.sample.shared.domain.model

/**
 * Created by Dima Kovalenko.
 */
data class CountryRow(
    val country: String? = null,
    val totalCases: Int? = null,
    val totalDeaths: Int? = null,
    val newCases: Int? = null,
    val newDeaths: Int? = null
)
