package com.multiplatform.sample.androidApp.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.multiplatform.sample.androidApp.R
import com.multiplatform.sample.androidApp.Utils
import com.multiplatform.sample.shared.entity.Row
import com.neovisionaries.i18n.CountryCode
import java.util.*


class ListAdapter(
    private val rows: MutableList<Row>,
    private val userCountry: String?
) : RecyclerView.Adapter<ListAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RowViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val movie: Row = rows[position]
        holder.bind(movie, userCountry)
    }

    override fun getItemCount(): Int = rows.size

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
            row: Row,
            userCountry: String?
        ) {

            var countryText = row.country
            countryText?.let {
                try {
                    val code = CountryCode.findByName(countryText)[0].name
                    val loc = Locale("", code)
                    countryText = Utils.localeToEmoji(loc) + " "+  countryText
                } catch (e: Exception) {
                }
            }
            mCountryView?.text = countryText

            mTotalCasesView?.text = row.totalCases.toString()
            mTotalDeathsView?.text = row.totalDeaths.toString()
            mNewCasesView?.text = row.newCases.toString()
            mNewDeathsView?.text = row.newDeaths.toString()

            val isMyCountry = userCountry?.equals(row.country) ?: false
            itemView.setBackgroundColor(if (isMyCountry) itemView.resources.getColor(R.color.dark) else Color.TRANSPARENT)
        }
    }
}