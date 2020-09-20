package com.multiplatform.sample.shared

import com.multiplatform.sample.shared.entity.CountryItem
import com.multiplatform.sample.shared.sorting.TotalDeathsComparator
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    private val _pageData = MutableLiveData<List<CountryItem>>(emptyList())
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