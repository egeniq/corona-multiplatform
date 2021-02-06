package com.multiplatform.sample.shared.viewmodel

import co.touchlab.kermit.Kermit
import com.multiplatform.sample.shared.domain.DataTransformer
import com.multiplatform.sample.shared.datasource.HopkinsAPI
import com.multiplatform.sample.shared.di.KodeinInjector
import com.multiplatform.sample.shared.domain.Response
import com.multiplatform.sample.shared.repo.CoronaRepository
import com.multiplatform.sample.shared.sorting.TotalDeathsComparator
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    private val repository by KodeinInjector.instance<CoronaRepository>()

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
                val data = repository.getData()
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
    }
}
