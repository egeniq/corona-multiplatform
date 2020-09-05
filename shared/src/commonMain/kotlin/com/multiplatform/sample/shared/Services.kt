package com.multiplatform.sample.shared

/**
 * Created by Dima Kovalenko.
 */
object Services {

    private const val BASE_URL = "https://pomber.github.io/covid19/"

    val helloAPI = HopkinsAPI(BASE_URL)
}