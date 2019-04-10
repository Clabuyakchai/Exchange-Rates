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
import com.google.android.material.snackbar.Snackbar
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
        viewModel.listCurrency.observe(this, Observer { list -> adapterCurrency.updateList(list) })
        viewModel.error.observe(this, Observer { error -> if (error) showError() else setHasOptionsMenu(true) })
        first_date.text = DayHelper.firstDate
        second_date.text = DayHelper.secondDate
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

    private fun showError() {
        if (view != null) {
            Snackbar.make(view!!, getString(R.string.error_message), Snackbar.LENGTH_LONG).show()
        }
        setHasOptionsMenu(false)
    }

    companion object {
        fun newInstance() = CurrencyListFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

}