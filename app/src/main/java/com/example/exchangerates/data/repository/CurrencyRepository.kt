package com.example.exchangerates.data.repository

import com.example.exchangerates.data.remote.dto.CurrencyResponse
import io.reactivex.Single

interface CurrencyRepository {
    fun getCurrencyFromApi(onDate: String): Single<List<CurrencyResponse>>
}