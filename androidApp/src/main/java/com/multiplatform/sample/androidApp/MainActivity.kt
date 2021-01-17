package com.multiplatform.sample.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.multiplatform.sample.androidApp.databinding.ActivityMainBinding
import com.multiplatform.sample.androidApp.ui.ListAdapter
import com.multiplatform.sample.shared.MainViewModel
import com.multiplatform.sample.shared.Response

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private var listAdapter: ListAdapter? = null

    private val observer: (Response?) -> Unit = {
        listAdapter?.countryItems = it?.items
        listAdapter?.notifyDataSetChanged()
    }

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
    }

    override fun onResume() {
        super.onResume()
        viewModel.pageData.addObserver(observer)
        viewModel.fetchData()
    }

    override fun onPause() {
        viewModel.pageData.removeObserver(observer)
        super.onPause()
    }

}
