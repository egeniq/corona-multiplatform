package com.multiplatform.sample.shared.domain.sorting

import com.multiplatform.sample.shared.datasource.db.CountryRow

/**
 * Created by Dima Kovalenko.
 */
class AlphabetComparator : Comparator<CountryRow?> {
    override fun compare(o1: CountryRow?, o2: CountryRow?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o1.country?.compareTo(o2.country ?: "") ?: 0
        }
    }
}
