package com.multiplatform.sample.shared.datasource

import co.touchlab.kermit.Kermit
import com.multiplatform.sample.shared.domain.model.Day
import com.multiplatform.sample.shared.getKermit
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

/**
 * Created by Dima Kovalenko.
 */
class HopkinsAPI {

    private val kermit = getKermit()

    private val client by lazy {
        kermit.d { "init ktor HttpClient" }
        try {
            HttpClient {
                install(JsonFeature) {
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.BODY
                }
            }
        } catch (e: Exception) {
            kermit.e(throwable = e) { "Error while init ktor HttpClient" }
            null
        }
    }

    suspend fun getCountryDaysMap(): Map<String, List<Day>>? {
        return client?.get(BASE_URL + "timeseries.json")
    }

    companion object {
        const val BASE_URL = "https://pomber.github.io/covid19/"
    }
}
