package com.multiplatform.sample.shared.datasource

import com.multiplatform.sample.shared.domain.model.Day
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

/**
 * Created by Dima Kovalenko.
 */
class HopkinsAPI(private val baseUrl: String) {

    private val client = HttpClient {
        install(JsonFeature) {
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }

    suspend fun getData(): Map<String, List<Day>> {
        return client.get(baseUrl + "timeseries.json")
    }
}
