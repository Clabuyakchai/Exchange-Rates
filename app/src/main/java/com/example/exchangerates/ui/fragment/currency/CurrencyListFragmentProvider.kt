package com.example.exchangerates.ui.fragment.currency

import com.example.exchangerates.di.scope.FragmentScope
import com.example.exchangerates.ui.fragment.currency.CurrencyListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CurrencyListFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    fun provideCurrencyListFragmentFactory(): CurrencyListFragment
}