package com.multiplatform.sample.androidApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.multiplatform.sample.androidApp.ui.ListAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.pageData.observe(this, Observer {
            listAdapter?.countryItems = it
            listAdapter?.notifyDataSetChanged()
        })

        val userCountryISO = Utils.getUserCountry(this)
        val userCountry = Utils.getCountryNameByISO(userCountryISO)
        val userCountryMapped = Utils.mapCountryName(userCountry)
        listAdapter = ListAdapter(userCountryMapped)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = listAdapter
    }
}
