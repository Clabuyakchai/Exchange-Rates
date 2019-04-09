package com.example.exchangerates.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exchangerates.di.scope.ViewModelKey
import com.example.exchangerates.ui.fragment.currency.CurrencyListFragmentViewModel
import com.example.exchangerates.ui.fragment.setting.CurrencyListSettingsFragmentViewModel
import com.example.exchangerates.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListFragmentViewModel::class)
    fun currencyListFragment(viewModel: CurrencyListFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListSettingsFragmentViewModel::class)
    fun currencyListSettingsFragment(viewModel: CurrencyListSettingsFragmentViewModel): ViewModel
}