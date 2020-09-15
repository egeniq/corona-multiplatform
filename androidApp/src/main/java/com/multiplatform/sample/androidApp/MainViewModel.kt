package com.multiplatform.sample.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multiplatform.sample.shared.Services
import com.multiplatform.sample.shared.entity.Day
import com.multiplatform.sample.shared.entity.CountryItem
import com.multiplatform.sample.shared.sorting.TotalDeathsComparator
import kotlinx.coroutines.launch

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    private val _pageData = MutableLiveData<List<CountryItem>>()
    val pageData: LiveData<List<CountryItem>>
        get() = _pageData

    init {
        viewModelScope.launch {
            try {
                val data = Services.helloAPI.getData()
                _pageData.postValue(transform(data))
            } catch (e: Exception) {
            }
        }
    }

    private fun transform(entries: Map<String, List<Day>>?): List<CountryItem> {
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
        rows.sortWith(TotalDeathsComparator())
        return rows
    }
}