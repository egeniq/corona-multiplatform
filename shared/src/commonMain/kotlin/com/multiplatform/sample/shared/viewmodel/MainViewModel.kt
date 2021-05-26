package com.multiplatform.sample.shared.viewmodel

import com.multiplatform.sample.shared.datasource.db.CountryRow
import com.multiplatform.sample.shared.repo.CoronaRepository
import com.multiplatform.sample.shared.domain.sorting.TotalDeathsComparator
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import com.multiplatform.sample.shared.utils.Result

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    val repository = CoronaRepository()

    private val _pageResultLD = MutableLiveData<Result<List<CountryRow>>>(Result.inProgress())
    val pageResultLD: LiveData<Result<List<CountryRow>>>
        get() = _pageResultLD

    fun fetchData() {
        viewModelScope.launch {
            try {
                val data = repository.getData().toMutableList()
                data.sortWith(TotalDeathsComparator())
                _pageResultLD.postValue(Result.success(data))
            } catch (e: Exception) {
                _pageResultLD.postValue(Result.failure(e))
            }
        }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}
