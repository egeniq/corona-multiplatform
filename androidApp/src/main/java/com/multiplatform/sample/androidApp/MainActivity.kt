package com.multiplatform.sample.androidApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.multiplatform.sample.androidApp.databinding.ActivityMainBinding
import com.multiplatform.sample.androidApp.ui.ListAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layout = R.layout.activity_main
    private val viewModel by viewModels<MainViewModel>()
    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.pageData.observe(this, Observer {
            listAdapter?.countryItems = it
            listAdapter?.notifyDataSetChanged()
        })

        val userCountryISO = Utils.getUserCountry(this)
        val userCountry = Utils.getCountryNameByISO(userCountryISO)
        val userCountryMapped = Utils.mapCountryName(userCountry)
        listAdapter = ListAdapter(userCountryMapped)
        binding.recyclerView.adapter = listAdapter
    }
}
