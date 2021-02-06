package com.multiplatform.sample.shared.domain.sorting

import com.multiplatform.sample.shared.domain.model.CountryRow

/**
 * Created by Dima Kovalenko.
 */
class TotalDeathsComparator : Comparator<CountryRow?> {
    override fun compare(o1: CountryRow?, o2: CountryRow?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o2.totalDeaths?.compareTo(o1.totalDeaths ?: 0) ?: 0
        }
    }
}
