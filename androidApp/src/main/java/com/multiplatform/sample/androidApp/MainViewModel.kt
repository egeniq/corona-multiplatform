package com.multiplatform.sample.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multiplatform.sample.shared.Services
import com.multiplatform.sample.shared.entity.Day
import kotlinx.coroutines.launch

/**
 * Created by Dima Kovalenko.
 */
class MainViewModel : ViewModel() {

    private val _pageData = MutableLiveData<Map<String, List<Day>>>()
    val pageData: LiveData<Map<String, List<Day>>>
        get() = _pageData

    init {
        viewModelScope.launch {
            try {
                unwrapHomePageBody(
                    Services.helloAPI.getData()
                )
            } catch (e: Exception) {
            }
        }
    }

    private suspend fun unwrapHomePageBody(response: HttpResponse<Page>) {
        try {
            _pageData.value = response.body()
        } catch (e: Exception) {
        }
    }

}