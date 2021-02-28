package com.multiplatform.sample.shared.domain.sorting

import com.multiplatform.sample.shared.domain.model.CountryData

/**
 * Created by Dima Kovalenko.
 */
class AlphabetComparator : Comparator<CountryData?> {
    override fun compare(o1: CountryData?, o2: CountryData?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o1.country?.compareTo(o2.country ?: "") ?: 0
        }
    }
}
