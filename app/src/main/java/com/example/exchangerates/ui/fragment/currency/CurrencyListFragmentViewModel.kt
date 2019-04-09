package com.example.exchangerates.ui.fragment.currency

import com.example.exchangerates.data.interactor.CurrencyInteractor
import com.example.exchangerates.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CurrencyListFragmentViewModel @Inject constructor(private val currencyInteractor: CurrencyInteractor): BaseViewModel() {

    val query = currencyInteractor.getCurrencyFromApi("2019-04-09").observeOn(AndroidSchedulers.mainThread())
}