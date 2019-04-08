package com.example.exchangerates.data.remote

import com.example.exchangerates.data.remote.dto.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("API/ExRates/Rates")
    fun getCurrencyList(@Query("onDate") onDate: String) : Single<List<CurrencyResponse>>
}