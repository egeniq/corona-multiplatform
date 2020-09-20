package com.multiplatform.sample.shared

import com.multiplatform.sample.shared.entity.CountryItem
import com.multiplatform.sample.shared.entity.Day

/**
 * Created by Dima Kovalenko.
 */
object DataTransformer {

    fun transform(entries: Map<String, List<Day>>?): MutableList<CountryItem> {
        val rows = mutableListOf<CountryItem>()
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
                            CountryItem(
                                country,
                                yesterday.confirmed,
                                yesterday.deaths,
                                newCases,
                                newDeaths
                            )
                        )
                    }
                }
            }
        }
        return rows
    }

}