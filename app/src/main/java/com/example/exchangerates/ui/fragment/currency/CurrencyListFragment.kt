package com.example.exchangerates.ui.fragment.currency

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.ui.activity.FragmentStack
import com.example.exchangerates.ui.activity.MainActivity
import com.example.exchangerates.ui.base.BaseFragment
import com.example.exchangerates.ui.fragment.currency.adapter.CurrencyListAdapter
import com.example.exchangerates.ui.fragment.setting.CurrencyListSettingsFragment
import com.example.exchangerates.util.DayHelper
import kotlinx.android.synthetic.main.fragment_list_currency.*
import kotlin.reflect.KClass

class CurrencyListFragment : BaseFragment<CurrencyListFragmentViewModel>() {

    private lateinit var adapterCurrency: CurrencyListAdapter
    private lateinit var recyclerView: RecyclerView
    private val fragmentStack: FragmentStack by lazy { activity as MainActivity }

    override fun getViewModelClass(): KClass<CurrencyListFragmentViewModel> = CurrencyListFragmentViewModel::class

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        adapterCurrency = CurrencyListAdapter()
        recyclerView = view!!.findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCurrency
        }

        viewModel.getCurrency()
    }

    override fun onStart() {
        super.onStart()
        subscribeToData()
    }

    private fun subscribeToData() {
        DayHelper.mutableFirstDay.observe(this, Observer { day.text = it })
        DayHelper.mutableSecondDay.observe(this, Observer { next_day.text = it })
        viewModel.listCurrency.observe(this, Observer { list -> adapterCurrency.updateList(list) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_currency_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.setting_menu -> {
            fragmentStack.add(CurrencyListSettingsFragment.newInstance(), true)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = CurrencyListFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

}