package com.multiplatform.sample.androidApp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.multiplatform.sample.androidApp.databinding.ActivityMainBinding
import com.multiplatform.sample.androidApp.ui.ListAdapter
import com.multiplatform.sample.shared.viewmodel.MainViewModel
import com.multiplatform.sample.shared.domain.model.CountryRow
import com.multiplatform.sample.shared.utils.Result
import com.multiplatform.sample.shared.utils.resolve

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private var listAdapter: ListAdapter? = null
    private var binding: ActivityMainBinding? = null

    private val pageObserver: (Result<List<CountryRow>>) -> Unit = { result ->
        result.resolve ({
            listAdapter?.countryItems = it
            listAdapter?.notifyDataSetChanged()
        }, {
            // handle error
        })
        binding?.progressBar?.visibility = if (result.inProgress()) View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.lifecycleOwner = this
        val view = binding?.root
        setContentView(view)

        viewModel = ViewModelProvider(this ).get(MainViewModel::class.java)

        val userCountryISO = Utils.getUserCountry(this)
        val userCountry = Utils.getCountryNameByISO(userCountryISO)
        val userCountryMapped = Utils.mapCountryName(userCountry)
        listAdapter = ListAdapter(userCountryMapped)
        binding?.recyclerView?.adapter = listAdapter

        viewModel.fetchData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.pageResultLD.addObserver(pageObserver)
    }

    override fun onPause() {
        viewModel.pageResultLD.removeObserver(pageObserver)
        super.onPause()
    }

}
