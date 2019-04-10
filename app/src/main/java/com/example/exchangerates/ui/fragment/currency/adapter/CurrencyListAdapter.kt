package com.example.exchangerates.ui.fragment.currency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.local.entity.CurrencyEntity
import kotlinx.android.synthetic.main.list_currency_item.view.*

class CurrencyListAdapter() :
    RecyclerView.Adapter<CurrencyListAdapter.CurrencyListHolder>() {

    private var currencyList = listOf<CurrencyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_currency_item, parent, false)

        return CurrencyListHolder(view)
    }

    override fun getItemCount(): Int = currencyList.size


    override fun onBindViewHolder(holder: CurrencyListHolder, position: Int) {
        holder.bind(currencyList.get(position))
    }

    fun updateList(currencyList: List<CurrencyEntity>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    class CurrencyListHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(list: CurrencyEntity) {
            itemView.apply {
                currency_symbol.text = list.symbol
                one_currency.text = itemView.resources.getString(R.string.currency_name, list.scale, list.name)
                first_rate.text = itemView.resources.getString(R.string.currency_rate, list.firstRate)
                second_rate.text = itemView.resources.getString(R.string.currency_rate, list.secondRate)
            }
        }
    }


}