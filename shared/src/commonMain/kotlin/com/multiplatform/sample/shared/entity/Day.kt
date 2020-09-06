package com.multiplatform.sample.shared.entity

//import kotlinx.serialization.Serializable

data class Day(
    val date: String? = null,
    val recovered: Int? = null,
    val confirmed: Int? = null,
    val deaths: Int? = null
)
