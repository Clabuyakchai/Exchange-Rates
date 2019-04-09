package com.example.exchangerates.ui.fragment.setting

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.ui.activity.FragmentStack
import com.example.exchangerates.ui.activity.MainActivity
import com.example.exchangerates.ui.base.BaseFragment
import com.example.exchangerates.ui.fragment.currency.adapter.CurrencyListAdapter
import kotlinx.android.synthetic.main.fragment_list_settings.*
import kotlin.reflect.KClass

class CurrencyListSettingsFragment : BaseFragment<CurrencyListSettingsFragmentViewModel>() {


    private lateinit var adapter: CurrencyListAdapter
    private lateinit var recyclerView: RecyclerView
    private val fragmentStack: FragmentStack by lazy { activity as MainActivity }

    override fun getViewModelClass(): KClass<CurrencyListSettingsFragmentViewModel> = CurrencyListSettingsFragmentViewModel::class

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        recyclerView = view.findViewById(R.id.recycler_view_settings)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_settings -> {//TODO
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