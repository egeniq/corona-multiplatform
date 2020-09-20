package com.multiplatform.sample.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multiplatform.sample.shared.DataTransformer
import com.multiplatform.sample.shared.Services
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
                val transformed = DataTransformer.transform(data)
                transformed.sortWith(TotalDeathsComparator())
                _pageData.postValue(transformed)
            } catch (e: Exception) {
            }
        }
    }


}