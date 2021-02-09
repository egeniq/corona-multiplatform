package com.multiplatform.sample.shared.repo

import com.multiplatform.sample.shared.datasource.HopkinsAPI
import com.multiplatform.sample.shared.di.KodeinInjector
import com.multiplatform.sample.shared.domain.CountryListDataMapper
import com.multiplatform.sample.shared.domain.model.CountryRow
import org.kodein.di.erased.instance

/**
 * Created by Dima Kovalenko.
 */
class CoronaRepository {

    private val hopkinsAPI by KodeinInjector.instance<HopkinsAPI>()

    suspend fun getData(): MutableList<CountryRow> {
        val countryRowList = CountryListDataMapper.map(hopkinsAPI.getCountryDaysMap())
        // todo cache
        return countryRowList
    }
}
