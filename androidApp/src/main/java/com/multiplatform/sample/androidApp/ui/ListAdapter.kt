package com.multiplatform.sample.androidApp.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.multiplatform.sample.androidApp.R
import com.multiplatform.sample.androidApp.Utils
import com.multiplatform.sample.shared.entity.CountryItem
import com.neovisionaries.i18n.CountryCode
import java.util.*

class ListAdapter(private val userCountry: String?) :
    RecyclerView.Adapter<ListAdapter.RowViewHolder>() {

    var countryItems: List<CountryItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RowViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        countryItems?.get(position)?.let {
            holder.bind(it, userCountry)
        }
    }

    override fun getItemCount(): Int = countryItems?.size ?: 0

    class RowViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

        private var mCountryView: TextView? = null
        private var mTotalCasesView: TextView? = null
        private var mTotalDeathsView: TextView? = null
        private var mNewCasesView: TextView? = null
        private var mNewDeathsView: TextView? = null

        init {
            mCountryView = itemView.findViewById(R.id.country)
            mTotalCasesView = itemView.findViewById(R.id.totalCases)
            mTotalDeathsView = itemView.findViewById(R.id.totalDeaths)
            mNewCasesView = itemView.findViewById(R.id.newCases)
            mNewDeathsView = itemView.findViewById(R.id.newDeaths)
        }

        fun bind(
            countryItem: CountryItem,
            userCountry: String?
        ) {

            var country = countryItem.country
            country?.let {
                try {
                    val code = CountryCode.findByName(mapToCountryCode(country))[0].name
                    val loc = Locale("", code)
                    country = Utils.localeToEmoji(loc) + "  " + country
                } catch (e: Exception) {
                }
            }
            mCountryView?.text = country

            mTotalCasesView?.text = countryItem.totalCases.toKs()
            mTotalDeathsView?.text = countryItem.totalDeaths.toKs()
            mNewCasesView?.text = countryItem.newCases.toKs()
            mNewDeathsView?.text = countryItem.newDeaths.toKs()

            val isMyCountry = userCountry?.equals(countryItem.country) ?: false
            itemView.setBackgroundColor(if (isMyCountry) itemView.resources.getColor(R.color.dark) else Color.TRANSPARENT)
        }

        private fun mapToCountryCode(country: String?): String? {
            return when (country) {
                "US" -> "United States"
                "Iran" -> "Iran, Islamic Republic of"
                "Russia" -> "Russian Federation"
                "Bolivia" -> "Bolivia, Plurinational State of"
                "Czechia" -> "Czech Republic"
                "Moldova" -> "Moldova, Republic of"
                "Korea, South" -> "Korea, Republic of"
                else -> country
            }
        }
    }
}

fun Int?.toKs(): String {
    return when (this) {
        null -> "no data"
        in 10000..Int.MAX_VALUE -> "${this / 1000}k"
        else -> this.toString()
    }
}