package com.example.exchangerates.ui.fragment.currency

import androidx.lifecycle.MutableLiveData
import com.example.exchangerates.data.interactor.CurrencyInteractor
import com.example.exchangerates.data.local.entity.CurrencyEntity
import com.example.exchangerates.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import javax.inject.Inject

class CurrencyListFragmentViewModel @Inject constructor(private val currencyInteractor: CurrencyInteractor) :
    BaseViewModel() {

//    val query = currencyInteractor.getCurrencyFromApi().observeOn(AndroidSchedulers.mainThread())

    val listCurrency = MutableLiveData<List<CurrencyEntity>>()

    fun getCurrency() = currencyInteractor
        .getCurrencyFromApi().observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { list -> listCurrency.postValue(list) },
            { t -> println(t.printStackTrace()) }).let { compositeDisposable.add(it) }
}