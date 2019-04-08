package com.example.exchangerates.data.interactor

import com.example.exchangerates.data.remote.dto.CurrencyResponse
import io.reactivex.Single

interface CurrencyInteractor {
    fun getCurrencyFromApi(onDate: String): Single<List<CurrencyResponse>>
}