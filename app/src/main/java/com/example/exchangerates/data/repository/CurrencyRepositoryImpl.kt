package com.example.exchangerates.data.repository

import com.example.exchangerates.data.local.dao.CurrencyDao
import com.example.exchangerates.data.local.entity.CurrencyEntity
import com.example.exchangerates.data.remote.CurrencyApi
import com.example.exchangerates.data.remote.dto.CurrencyResponse
import com.example.exchangerates.util.DayHelper
import com.example.exchangerates.util.DefaultCurrencyShow
import com.example.exchangerates.util.toApiFormat
import com.example.exchangerates.util.toUIFormat
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.Action
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val currencyDao: CurrencyDao
) : CurrencyRepository {

    override fun getCurrencyFromApi(): Single<List<CurrencyEntity>> {
        return currencyApi.getCurrencyList(DayHelper.today.toApiFormat())
            .flatMap { today ->
                currencyApi.getCurrencyList(DayHelper.tomorrow.toApiFormat())
                    .flatMap { tomorrow ->
                        if (tomorrow.isEmpty()) {
                            currencyApi.getCurrencyList(DayHelper.yesterday.toApiFormat())
                                .flatMap { yesterday -> Single.just(convert(yesterday, today)) }
                        } else {
                            Single.just(convert(today, tomorrow))
                        }
                    }
            }
    }

    override fun insertChangeListCurrency(currencyList: List<CurrencyEntity>): Completable =
        Completable.fromAction { currencyDao.insertList(currencyList) }
            .subscribeOn(Schedulers.io())


    override fun getAllFromDB(): Single<List<CurrencyEntity>> = currencyDao.getAll().subscribeOn(Schedulers.io())

    private fun convert(firstDay: List<CurrencyResponse>, secondDay: List<CurrencyResponse>): List<CurrencyEntity> {
        val currencyEntity = mutableListOf<CurrencyEntity>()

        for (i in firstDay.indices) {
            currencyEntity.add(
                CurrencyEntity(
                    firstDay.get(i).Cur_ID,
                    firstDay.get(i).Cur_Abbreviation,
                    firstDay.get(i).Cur_Name,
                    firstDay.get(i).Cur_OfficialRate,
                    secondDay.get(i).Cur_OfficialRate,
                    firstDay.get(i).Cur_Scale,
                    true, i
                ).also { currency ->
                    val currencyDB = currencyDao.getCurrencyById(currency.id)
                    currency.isShow = currencyDB?.isShow ?: setDefaultCurrencyShow(currency.symbol)
                    currency.position = currencyDB?.position ?: currency.position
                }
            )
        }

        //TODO
        currencyDao.insertList(currencyEntity)

        return currencyEntity
    }

    private fun setDefaultCurrencyShow(symbol: String): Boolean {
        DefaultCurrencyShow.listDefaultCurrencyShow.forEach { if (it == symbol) return true }
        return false
    }

}