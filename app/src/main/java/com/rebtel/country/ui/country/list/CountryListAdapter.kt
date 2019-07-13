package com.rebtel.country.ui.country.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rebtel.country.R
import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.ui.country.detail.CountryDetailActivity


class CountryListAdapter(private val items: List<CountryResponseDataModel>?) :
    RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.list_item_country, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items!!.get(position))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CountryResponseDataModel) {

            itemView.setOnClickListener {
                val context = itemView.context
                val maintenanceHistoryIntent = Intent(context, CountryDetailActivity::class.java)

                CountryDetailActivity.itemCountry = item
                context.startActivity(maintenanceHistoryIntent)
            }
        }
    }
}