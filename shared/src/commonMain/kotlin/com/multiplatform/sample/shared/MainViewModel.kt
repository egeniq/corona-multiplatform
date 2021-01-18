package com.multiplatform.sample.shared

import com.multiplatform.sample.shared.sorting.TotalDeathsComparator
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    private val _pageData = MutableLiveData<Response?>(null)
    val pageData: LiveData<Response?>
        get() = _pageData

    fun fetchData() {
//        Log.debug(TAG + "fetchData()")
        viewModelScope.launch {
            try {
                val data = Services.helloAPI.getData()
                val transformed = DataTransformer.transform(data)
                transformed.sortWith(TotalDeathsComparator())
                _pageData.postValue(Response(transformed))
//                Log.debug(TAG + "Downloaded data successfully")
            } catch (e: Exception) {
//                Log.assert(TAG + "Error while loading data", e)
            }
        }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}