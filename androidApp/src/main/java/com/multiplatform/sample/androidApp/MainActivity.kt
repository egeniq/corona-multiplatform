package com.multiplatform.sample.androidApp

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.multiplatform.sample.androidApp.databinding.ActivityMainBinding
import com.multiplatform.sample.androidApp.ui.ListAdapter
import com.multiplatform.sample.shared.MainViewModel
import dev.icerock.moko.mvvm.createViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId = R.layout.activity_main
    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java
    override val viewModelVariableId: Int = BR._all
    override fun viewModelFactory(): ViewModelProvider.Factory {
        return createViewModelFactory { MainViewModel() }
    }

    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        viewModel.pageData.addObserver {
            listAdapter?.countryItems = it?.items
            listAdapter?.notifyDataSetChanged()
        }

        val userCountryISO = Utils.getUserCountry(this)
        val userCountry = Utils.getCountryNameByISO(userCountryISO)
        val userCountryMapped = Utils.mapCountryName(userCountry)
        listAdapter = ListAdapter(userCountryMapped)
        binding.recyclerView.adapter = listAdapter
    }


}
