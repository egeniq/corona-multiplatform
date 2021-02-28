package com.multiplatform.sample.shared.domain.sorting

import com.multiplatform.sample.shared.domain.model.CountryData

/**
 * Created by Dima Kovalenko.
 */
class TotalDeathsComparator : Comparator<CountryData?> {
    override fun compare(o1: CountryData?, o2: CountryData?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o2.totalDeaths?.compareTo(o1.totalDeaths ?: 0) ?: 0
        }
    }
}
