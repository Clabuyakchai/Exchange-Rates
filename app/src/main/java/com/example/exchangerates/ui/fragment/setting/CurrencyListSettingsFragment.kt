package com.example.exchangerates.ui.fragment.setting

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.ui.activity.FragmentStack
import com.example.exchangerates.ui.activity.MainActivity
import com.example.exchangerates.ui.base.BaseFragment
import com.example.exchangerates.ui.fragment.setting.adapter.CurrencyItemTouchHelper
import com.example.exchangerates.ui.fragment.setting.adapter.CurrencyListSettingsAdapter
import kotlinx.android.synthetic.main.fragment_list_settings.*
import kotlin.reflect.KClass

class CurrencyListSettingsFragment : BaseFragment<CurrencyListSettingsFragmentViewModel>() {

    private lateinit var adapterCurrency: CurrencyListSettingsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemTouchHelper: ItemTouchHelper
    private val fragmentStack: FragmentStack by lazy { activity as MainActivity }

    override fun getViewModelClass(): KClass<CurrencyListSettingsFragmentViewModel> =
        CurrencyListSettingsFragmentViewModel::class

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        adapterCurrency = CurrencyListSettingsAdapter()
        recyclerView = view!!.findViewById(R.id.recycler_view_settings)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply { recycleChildrenOnDetach = true }
            adapter = adapterCurrency
        }
        itemTouchHelper = ItemTouchHelper(CurrencyItemTouchHelper(adapterCurrency))
        itemTouchHelper.attachToRecyclerView(recyclerView)
        viewModel.getCurrency()
    }

    override fun onStart() {
        super.onStart()
        subscribeToData()
    }

    private fun subscribeToData(){
        viewModel.listCurrency.observe(this, Observer { list -> adapterCurrency.updateList(list) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_settings -> {
            viewModel.updateCurrency(adapterCurrency.currencyList)
            fragmentStack.pop()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    companion object {
        fun newInstance() = CurrencyListSettingsFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}