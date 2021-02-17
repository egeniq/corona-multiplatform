package com.multiplatform.sample.shared.repo

import com.multiplatform.sample.shared.datasource.HopkinsAPI
import com.multiplatform.sample.shared.domain.CountryListDataMapper
import com.multiplatform.sample.shared.domain.model.CountryRow

/**
 * Created by Dima Kovalenko.
 */
class CoronaRepository {

    private val hopkinsAPI  = HopkinsAPI()

    suspend fun getData(): MutableList<CountryRow> {
        val countryRowList = CountryListDataMapper.map(hopkinsAPI.getCountryDaysMap())
        // todo cache
        return countryRowList
    }
}
