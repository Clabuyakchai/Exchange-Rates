package com.example.exchangerates.ui.fragment.setting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.local.entity.CurrencyEntity
import kotlinx.android.synthetic.main.list_currency_settings_item.view.*
import java.util.*

class CurrencyListSettingsAdapter : RecyclerView.Adapter<CurrencyListSettingsAdapter.CurrencyListSettingsHolder>() {

    var currencyList = listOf<CurrencyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListSettingsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_currency_settings_item, parent, false)
        val holder = CurrencyListSettingsHolder(view)

        holder.itemView.show.setOnClickListener { v ->
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                currencyList.get(position).isShow = !currencyList.get(position).isShow
            } }
        return holder
    }

    override fun getItemCount(): Int = currencyList.size

    override fun onBindViewHolder(holder: CurrencyListSettingsHolder, position: Int) {
        holder.bind(currencyList.get(position))
    }

    fun updateList(currencyList: List<CurrencyEntity>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition)
            for (i in fromPosition until toPosition) {
                Collections.swap(currencyList, i, i + 1)
            }
        else
            for (i in fromPosition downTo (toPosition + 1)){
                Collections.swap(currencyList, i, i - 1)
            }
        notifyItemMoved(fromPosition, toPosition)
    }

    class CurrencyListSettingsHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(list: CurrencyEntity) {
            itemView.apply {
                currency_symbol.text = list.symbol
                one_currency.text = itemView.resources.getString(R.string.currency_name, list.scale, list.name)
                show.isChecked = list.isShow
            }
        }
    }
}