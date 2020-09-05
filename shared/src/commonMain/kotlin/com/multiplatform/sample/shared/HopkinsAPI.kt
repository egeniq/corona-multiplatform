package com.multiplatform.sample.shared

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

/**
 * Created by Dima Kovalenko.
 */
class HopkinsAPI(private val baseUrl: String) {

    private val client = HttpClient {
        expectSuccess = false
    }

    suspend fun getData(): HttpResponse? {
        val response = client.request<HttpResponse> {
            url(baseUrl + "timeseries.json")
            method = HttpMethod.Get
        }
        return null
    }

}