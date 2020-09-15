package com.multiplatform.sample.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multiplatform.sample.shared.Services
import com.multiplatform.sample.shared.entity.Day
import com.multiplatform.sample.shared.entity.Row
import kotlinx.coroutines.launch

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    private val _pageData = MutableLiveData<List<Row>>()
    val pageData: LiveData<List<Row>>
        get() = _pageData

    init {
        viewModelScope.launch {
            try {
                unwrapHomePageBody(Services.helloAPI.getData())
            } catch (e: Exception) {
            }
        }
    }

//    private fun onResponseReady(entries: Map<String, List<Day>>?) {
//        progressBar.visibility = View.GONE
//
//        val rows = mutableListOf<Row>()
//
//        entries?.keys?.forEach { country ->
//            val days = entries[country]
//            days?.let {
//                if (days.size > 2) {
//                    val yesterday = days[days.size - 1]
//                    val dayBeforeYesterday = days[days.size - 2]
//
//                    var newCases = yesterday.confirmed ?: 0
//                    newCases -= dayBeforeYesterday.confirmed ?: 0
//
//                    var newDeaths = yesterday.deaths ?: 0
//                    newDeaths -= dayBeforeYesterday.deaths ?: 0
//
//                    if (newCases > 0) {
//                        val row =
//                            Row(country, yesterday.confirmed, yesterday.deaths, newCases, newDeaths)
//                        rows.add(row)
//                    }
//                }
//            }
//        }
//
//        val userCountryISO = Utils.getUserCountry(this)
//        val userCountry = Utils.getCountryNameByISO(userCountryISO)
//        val userCountryMapped = Utils.mapCountryName(userCountry)
//        listAdapter = ListAdapter(rows, userCountryMapped)
//        rows.sortWith(TotalDeathsComparator())
//        recyclerView.adapter = listAdapter
//    }

    private suspend fun unwrapHomePageBody(data: Map<String, List<Day>>) {
        try {
//            _pageData.value = response.body()
        } catch (e: Exception) {
        }
    }

}