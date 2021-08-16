package com.multiplatform.sample.shared.domain

import com.multiplatform.sample.shared.datasource.db.CountryRow
import com.multiplatform.sample.shared.domain.model.Day

/**
 * Created by Dima Kovalenko.
 */
object CountryListDataMapper {

    fun map(entries: Map<String, List<Day>>?): MutableList<CountryRow> {
        val rows = mutableListOf<CountryRow>()
        entries?.keys?.forEach { country ->
            val days = entries[country]
            days?.let {
                if (days.size > 2) {
                    val yesterday = days[days.size - 1]
                    val dayBeforeYesterday = days[days.size - 2]

                    var newCases = yesterday.confirmed ?: 0
                    newCases -= dayBeforeYesterday.confirmed ?: 0

                    var newDeaths = yesterday.deaths ?: 0
                    newDeaths -= dayBeforeYesterday.deaths ?: 0

                    if (newCases > 0) {
                        rows.add(
                            CountryRow(
                                0,
                                country,
                                yesterday.confirmed?.toLong(),
                                yesterday.deaths?.toLong(),
                                newCases.toLong(),
                                newDeaths.toLong()
                            )
                        )
                    }
                }
            }
        }
        return rows
    }

}
