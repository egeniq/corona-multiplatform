package com.multiplatform.sample.shared.datasource

import com.multiplatform.sample.shared.domain.model.Day
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

/**
 * Created by Dima Kovalenko.
 */
class HopkinsAPI {

    private val client = HttpClient {
        install(JsonFeature) {
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }

    suspend fun getCountryDaysMap(): Map<String, List<Day>> {
        return client.get(BASE_URL + "timeseries.json")
    }

    companion object {
        const val BASE_URL = "https://pomber.github.io/covid19/"
    }
}
