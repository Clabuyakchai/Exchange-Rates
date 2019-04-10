package com.example.exchangerates.ui.fragment.setting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.local.entity.CurrencyEntity
import kotlinx.android.synthetic.main.list_currency_settings_item.view.*

class CurrencyListSettingsAdapter : RecyclerView.Adapter<CurrencyListSettingsAdapter.CurrencyListSettingsHolder>() {

    private var currencyList = listOf<CurrencyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListSettingsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_currency_settings_item, parent, false)

        return CurrencyListSettingsHolder(view)
    }

    override fun getItemCount(): Int = currencyList.size

    override fun onBindViewHolder(holder: CurrencyListSettingsHolder, position: Int) {
        holder.bind(currencyList.get(position))
    }

    fun updateList(currencyList: List<CurrencyEntity>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    class CurrencyListSettingsHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(currencyEntity: CurrencyEntity) {
            itemView.apply {
                currency_symbol.text = currencyEntity.symbol
                one_currency.text = "${currencyEntity.scale} ${currencyEntity.name}"
                show.isChecked = currencyEntity.isShow
            }
        }
    }
}