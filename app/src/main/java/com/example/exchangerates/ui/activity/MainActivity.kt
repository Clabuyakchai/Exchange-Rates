package com.example.exchangerates.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.exchangerates.R
import com.example.exchangerates.ui.fragment.currency.CurrencyListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), FragmentStack {

    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add(CurrencyListFragment.newInstance(), false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun add(fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = fm.beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackStack) fragmentTransaction.addToBackStack(fragment::class.java.name)

        fragmentTransaction.commit()
    }

    override fun pop() {
        fm.popBackStack()
    }
}
