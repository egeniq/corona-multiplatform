package com.multiplatform.sample.shared.repo

import com.multiplatform.sample.shared.datasource.HopkinsAPI
import com.multiplatform.sample.shared.domain.model.Day

/**
 * Created by Dima Kovalenko.
 */
class CoronaRepository {

    private val helloAPI = HopkinsAPI(BASE_URL)

    suspend fun getData(): Map<String, List<Day>> {
        return helloAPI.getData()
    }

    companion object {
        const val BASE_URL = "https://pomber.github.io/covid19/"
    }
}
