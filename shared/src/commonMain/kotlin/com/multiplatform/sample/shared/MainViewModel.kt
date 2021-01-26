package com.multiplatform.sample.shared

import co.touchlab.kermit.Kermit
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

    private var kermit: Kermit? = null

    fun setup(kermit: Kermit) {
        this.kermit = kermit
    }

    fun fetchData() {
        kermit?.d(TAG) { "fetchData()" }
        viewModelScope.launch {
            try {
                val helloAPI = HopkinsAPI(BASE_URL)
                val data = helloAPI.getData()
                val transformed = DataTransformer.transform(data)
                transformed.sortWith(TotalDeathsComparator())
                _pageData.postValue(Response(transformed))
                kermit?.d(TAG) { "Downloaded data successfully" }
            } catch (e: Exception) {
                kermit?.e(TAG, e) { "Error while loading data" }
            }
        }
    }

    companion object {
        const val TAG = "MainViewModel"
        const val BASE_URL = "https://pomber.github.io/covid19/"
    }
}