package com.example.exchangerates.data.interactor

import com.example.exchangerates.data.local.entity.CurrencyEntity
import com.example.exchangerates.data.repository.CurrencyRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CurrencyInteractorImpl @Inject constructor(private val currencyRepository: CurrencyRepository) :
    CurrencyInteractor {

    override fun getCurrencyFromApi(): Single<List<CurrencyEntity>> =
        currencyRepository.getCurrencyFromApi()

    override fun insertChangeListCurrency(currencyList: List<CurrencyEntity>): Completable =
        currencyRepository.insertChangeListCurrency(currencyList)

    override fun getAllFromDB(): Single<List<CurrencyEntity>> = currencyRepository.getAllFromDB()
}