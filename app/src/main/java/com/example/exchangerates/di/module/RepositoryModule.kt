package com.example.exchangerates.di.module

import com.example.exchangerates.data.interactor.CurrencyInteractor
import com.example.exchangerates.data.interactor.CurrencyInteractorImpl
import com.example.exchangerates.data.repository.CurrencyRepository
import com.example.exchangerates.data.repository.CurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun provideCurrencyRepository(repository: CurrencyRepositoryImpl): CurrencyRepository

    @Singleton
    @Binds
    fun provideCurrencyInteractor(repository: CurrencyInteractorImpl): CurrencyInteractor
}