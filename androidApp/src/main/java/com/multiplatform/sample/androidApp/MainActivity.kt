package com.multiplatform.sample.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.multiplatform.sample.androidApp.databinding.ActivityMainBinding
import com.multiplatform.sample.androidApp.ui.ListAdapter
import com.multiplatform.sample.shared.MainViewModel
import dev.icerock.moko.mvvm.createViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this ).get(MainViewModel::class.java)

        val userCountryISO = Utils.getUserCountry(this)
        val userCountry = Utils.getCountryNameByISO(userCountryISO)
        val userCountryMapped = Utils.mapCountryName(userCountry)
        listAdapter = ListAdapter(userCountryMapped)
        binding.recyclerView.adapter = listAdapter

        viewModel.pageData.addObserver {
            listAdapter?.countryItems = it?.items
            listAdapter?.notifyDataSetChanged()
        }
    }


}
