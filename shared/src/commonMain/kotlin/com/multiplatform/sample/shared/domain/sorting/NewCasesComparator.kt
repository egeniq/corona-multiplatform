package com.multiplatform.sample.shared.domain.sorting

import com.multiplatform.sample.shared.datasource.db.CountryRow


/**
 * Created by Dima Kovalenko.
 */
class NewCasesComparator : Comparator<CountryRow?> {
    override fun compare(o1: CountryRow?, o2: CountryRow?): Int {
        return when {
            o1 == null || o2 == null -> 0
            else -> o2.newCases?.compareTo(o1.newCases ?: 0) ?: 0
        }
    }
}
