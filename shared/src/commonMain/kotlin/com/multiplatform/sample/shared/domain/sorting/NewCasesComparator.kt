package com.multiplatform.sample.shared.domain.sorting

import com.multiplatform.sample.shared.domain.model.CountryData


/**
 * Created by Dima Kovalenko.
 */
class NewCasesComparator : Comparator<CountryData?> {
    override fun compare(o1: CountryData?, o2: CountryData?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o2.newCases?.compareTo(o1.newCases ?: 0) ?: 0
        }
    }
}
