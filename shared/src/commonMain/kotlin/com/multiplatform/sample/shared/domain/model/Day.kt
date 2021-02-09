package com.multiplatform.sample.shared.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Day(
    val date: String? = null,
    val recovered: Int? = null,
    val confirmed: Int? = null,
    val deaths: Int? = null
)
