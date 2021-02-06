package com.multiplatform.sample.shared.sorting

import com.multiplatform.sample.shared.domain.model.CountryItem


/**
 * Created by Dima Kovalenko.
 */
class TotalCasesComparator : Comparator<CountryItem?> {
    override fun compare(o1: CountryItem?, o2: CountryItem?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o2.totalCases?.compareTo(o1.totalCases ?: 0) ?: 0
        }
    }
}
