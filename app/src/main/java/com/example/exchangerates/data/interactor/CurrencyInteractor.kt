package com.example.exchangerates.data.interactor

import com.example.exchangerates.data.local.entity.CurrencyEntity
import io.reactivex.Completable
import io.reactivex.Single

interface CurrencyInteractor {
    fun getCurrencyFromApi(): Single<List<CurrencyEntity>>
    fun insertChangeListCurrency(currencyList: List<CurrencyEntity>): Completable
    fun getAllFromDB(): Single<List<CurrencyEntity>>
}