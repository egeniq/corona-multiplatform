package com.multiplatform.sample.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.multiplatform.sample.androidApp.databinding.ActivityMainBinding
import com.multiplatform.sample.androidApp.ui.ListAdapter
import com.multiplatform.sample.shared.viewmodel.MainViewModel
import com.multiplatform.sample.shared.domain.Response

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private var listAdapter: ListAdapter? = null

    private val pageObserver: (Response?) -> Unit = {
        listAdapter?.countryItems = it?.items
        listAdapter?.notifyDataSetChanged()
    }

    private val kermit = Kermit(LogcatLogger(), CommonLogger())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this ).get(MainViewModel::class.java)
        viewModel.setup(kermit)

        val userCountryISO = Utils.getUserCountry(this)
        val userCountry = Utils.getCountryNameByISO(userCountryISO)
        val userCountryMapped = Utils.mapCountryName(userCountry)
        listAdapter = ListAdapter(userCountryMapped)
        binding.recyclerView.adapter = listAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.pageData.addObserver(pageObserver)
        viewModel.fetchData()
    }

    override fun onPause() {
        viewModel.pageData.removeObserver(pageObserver)
        super.onPause()
    }

}
