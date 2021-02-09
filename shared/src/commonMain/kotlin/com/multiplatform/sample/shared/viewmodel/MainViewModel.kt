package com.multiplatform.sample.shared.viewmodel

import co.touchlab.kermit.Kermit
import com.multiplatform.sample.shared.di.KodeinInjector
import com.multiplatform.sample.shared.domain.model.CountryRow
import com.multiplatform.sample.shared.repo.CoronaRepository
import com.multiplatform.sample.shared.domain.sorting.TotalDeathsComparator
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance
import com.multiplatform.sample.shared.utils.Result

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    private val repository by KodeinInjector.instance<CoronaRepository>()

    private val _pageResultLD = MutableLiveData<Result<List<CountryRow>>>(Result.inProgress())
    val pageResultLD: LiveData<Result<List<CountryRow>>>
        get() = _pageResultLD

    private var kermit: Kermit? = null

    fun setup(kermit: Kermit) {
        this.kermit = kermit
    }

    fun fetchData() {
        kermit?.d(TAG) { "fetchData()" }
        viewModelScope.launch {
            try {
                val data = repository.getData()
                data.sortWith(TotalDeathsComparator())
                kermit?.d(TAG) { "Downloaded data successfully" }
                _pageResultLD.postValue(Result.success(data))
            } catch (e: Exception) {
                kermit?.e(TAG, e) { "Error while loading data" }
                _pageResultLD.postValue(Result.failure(e))
            }
        }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}
