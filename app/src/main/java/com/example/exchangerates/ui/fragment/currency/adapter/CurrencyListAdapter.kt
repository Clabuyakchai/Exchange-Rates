package com.example.exchangerates.ui.fragment.currency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.remote.dto.CurrencyResponse
import kotlinx.android.synthetic.main.list_currency_item.view.*

class CurrencyListAdapter(private var currencyList: List<CurrencyResponse>) :
    RecyclerView.Adapter<CurrencyListAdapter.CurrencyListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_currency_item, parent, false)

        return CurrencyListHolder(view)
    }

    override fun getItemCount(): Int = currencyList.size


    override fun onBindViewHolder(holder: CurrencyListHolder, position: Int) {
        holder.bind(currencyList.get(position))
    }

    fun updateList(currencyResponse: List<CurrencyResponse>) {
        currencyList = currencyResponse
        notifyDataSetChanged()
    }

    class CurrencyListHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(list: CurrencyResponse) {
            itemView.apply {
                currency_symbol.text = list.Cur_Abbreviation
                one_currency.text = "${list.Cur_Scale} ${list.Cur_Name}"
            }
        }
    }


}