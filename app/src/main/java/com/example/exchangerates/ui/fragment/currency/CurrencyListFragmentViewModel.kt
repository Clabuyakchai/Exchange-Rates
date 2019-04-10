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

    val listCurrency = MutableLiveData<List<CurrencyEntity>>()
    val error = MutableLiveData<Boolean>()

    fun getCurrency() = currencyInteractor
        .getCurrencyFromApi().observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { list ->
                error.postValue(false)
                listCurrency.postValue(list) },
            { t ->
                error.postValue(true)
                println(t.printStackTrace()) }).let { compositeDisposable.add(it) }
}