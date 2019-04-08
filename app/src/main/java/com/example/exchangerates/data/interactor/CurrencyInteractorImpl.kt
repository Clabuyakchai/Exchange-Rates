package com.example.exchangerates.data.interactor

import com.example.exchangerates.data.remote.dto.CurrencyResponse
import com.example.exchangerates.data.repository.CurrencyRepository
import io.reactivex.Single
import javax.inject.Inject

class CurrencyInteractorImpl @Inject constructor(private val currencyRepository: CurrencyRepository) :
    CurrencyInteractor {
    override fun getCurrencyFromApi(onDate: String): Single<List<CurrencyResponse>> =
        currencyRepository.getCurrencyFromApi(onDate)
}