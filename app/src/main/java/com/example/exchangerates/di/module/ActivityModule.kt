package com.example.exchangerates.di.module

import com.example.exchangerates.di.scope.ActivityScope
import com.example.exchangerates.ui.activity.MainActivity
import com.example.exchangerates.ui.fragment.currency.CurrencyListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [CurrencyListFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}