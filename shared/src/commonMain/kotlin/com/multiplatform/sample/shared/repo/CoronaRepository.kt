package com.multiplatform.sample.shared.repo

import com.multiplatform.sample.shared.CoronaDatabase
import com.multiplatform.sample.shared.datasource.HopkinsAPI
import com.multiplatform.sample.shared.datasource.db.CountryRow
import com.multiplatform.sample.shared.domain.CountryListDataMapper
import com.squareup.sqldelight.db.SqlDriver

/**
 * Created by Dima Kovalenko.
 */
class CoronaRepository {

    private val hopkinsAPI  = HopkinsAPI()

    var database: CoronaDatabase? = null

    fun setup(sqlDriver: SqlDriver) {               // TODO: this should be done via DI
        database = CoronaDatabase(sqlDriver)
    }

    suspend fun getData(): List<CountryRow> {

        val dataFromDb: List<CountryRow>? = database?.countryRowQueries?.selectAll()?.executeAsList()

        if (dataFromDb?.isNotEmpty() == true) {  // TODO add cache lifetime
            return dataFromDb
        } else {
            val countryRowList = CountryListDataMapper.map(hopkinsAPI.getCountryDaysMap())
            countryRowList.forEach {
                database?.countryRowQueries?.insertCountryRow(
                        null,
                        it.country,
                        it.totalCases,
                        it.totalDeaths,
                        it.newCases,
                        it.newDeaths
                )
            }
            return countryRowList
        }
    }
}
