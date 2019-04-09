package com.example.exchangerates.ui.fragment.setting

import com.example.exchangerates.di.scope.FragmentScope
import dagger.Component
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CurrencyListSettingsFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    fun provideCurrencyListSettingsFragmentFactory(): CurrencyListSettingsFragment
}