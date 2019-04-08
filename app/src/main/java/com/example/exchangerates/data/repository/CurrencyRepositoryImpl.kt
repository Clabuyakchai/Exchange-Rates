package com.example.exchangerates.data.repository

import com.example.exchangerates.data.remote.CurrencyApi
import com.example.exchangerates.data.remote.dto.CurrencyResponse
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val currencyApi: CurrencyApi) : CurrencyRepository {

    override fun getCurrencyFromApi(onDate: String): Single<List<CurrencyResponse>> =
        currencyApi.getCurrencyList(onDate)

}