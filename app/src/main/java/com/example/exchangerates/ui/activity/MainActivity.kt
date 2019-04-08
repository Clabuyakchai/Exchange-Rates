package com.example.exchangerates.ui.activity

import android.os.Bundle
import com.example.exchangerates.R
import com.example.exchangerates.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_list_currency.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_list_currency)

        //TODO
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
