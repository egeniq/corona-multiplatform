package com.multiplatform.sample.shared.sorting

import com.multiplatform.sample.shared.entity.CountryItem


/**
 * Created by Dima Kovalenko.
 */
class NewCasesComparator : Comparator<CountryItem?> {
    override fun compare(o1: CountryItem?, o2: CountryItem?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o2.newCases?.compareTo(o1.newCases ?: 0) ?: 0
        }
    }
}