package com.example.exchangerates.ui.activity

import androidx.fragment.app.Fragment

interface FragmentStack {
    fun add(fragment: Fragment, addToBackStack: Boolean)
    fun pop()
}