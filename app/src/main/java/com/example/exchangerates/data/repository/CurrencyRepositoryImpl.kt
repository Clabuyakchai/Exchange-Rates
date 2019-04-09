package com.example.exchangerates.data.repository

import com.example.exchangerates.data.local.dao.CurrencyDao
import com.example.exchangerates.data.local.entity.CurrencyEntity
import com.example.exchangerates.data.remote.CurrencyApi
import com.example.exchangerates.data.remote.dto.CurrencyResponse
import com.example.exchangerates.util.DayHelper
import com.example.exchangerates.util.toApiFormat
import com.example.exchangerates.util.toUIFormat
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val currencyApi: CurrencyApi, private val currencyDao: CurrencyDao) : CurrencyRepository {

    override fun getCurrencyFromApi(): Single<List<CurrencyEntity>> {
        val today = currencyApi.getCurrencyList(DayHelper.today.toApiFormat())
        val tomorrow = currencyApi.getCurrencyList(DayHelper.tomorrow.toApiFormat())
        val yesterday = currencyApi.getCurrencyList(DayHelper.yesterday.toApiFormat())

        return Single.zip(today, tomorrow, yesterday, Function3 { t1, t2, t3 -> convert(t1, t2, t3) })
    }

    private fun convert(
        today: List<CurrencyResponse>,
        tomorrow: List<CurrencyResponse>,
        yesterday: List<CurrencyResponse>
    ): List<CurrencyEntity> {
        val currencyEntity = mutableListOf<CurrencyEntity>()
        if (!tomorrow.isEmpty()) {
            DayHelper.mutableFirstDay.postValue(DayHelper.today.toUIFormat())
            DayHelper.mutableSecondDay.postValue(DayHelper.tomorrow.toUIFormat())
            for (i in tomorrow.indices) {
                currencyEntity.add(
                    CurrencyEntity(
                        today.get(i).Cur_ID,
                        today.get(i).Cur_Abbreviation,
                        today.get(i).Cur_Name,
                        today.get(i).Cur_OfficialRate,
                        tomorrow.get(i).Cur_OfficialRate,
                        today.get(i).Cur_Scale,
                        true, 0
                    )
                )
            }
        } else {
            for (i in today.indices) {
                DayHelper.mutableFirstDay.postValue(DayHelper.yesterday.toUIFormat())
                DayHelper.mutableSecondDay.postValue(DayHelper.today.toUIFormat())
                currencyEntity.add(
                    CurrencyEntity(
                        yesterday.get(i).Cur_ID,
                        yesterday.get(i).Cur_Abbreviation,
                        yesterday.get(i).Cur_Name,
                        yesterday.get(i).Cur_OfficialRate,
                        today.get(i).Cur_OfficialRate,
                        yesterday.get(i).Cur_Scale,
                        true, 0
                    )
                )
            }
        }
        return currencyEntity
    }

}