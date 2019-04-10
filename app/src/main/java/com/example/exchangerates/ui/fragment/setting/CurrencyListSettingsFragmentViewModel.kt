package com.example.exchangerates.ui.fragment.setting

import androidx.lifecycle.MutableLiveData
import com.example.exchangerates.data.interactor.CurrencyInteractor
import com.example.exchangerates.data.local.entity.CurrencyEntity
import com.example.exchangerates.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CurrencyListSettingsFragmentViewModel @Inject constructor(private val currencyInteractor: CurrencyInteractor) :
    BaseViewModel() {

    val listCurrency = MutableLiveData<List<CurrencyEntity>>()

    fun getCurrency() = currencyInteractor.getAllFromDB()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { list -> listCurrency.postValue(list) },
            { t -> println(t.printStackTrace()) }).let { compositeDisposable.add(it) }

    fun updateCurrency(currencyList: List<CurrencyEntity>){
        currencyInteractor.insertChangeListCurrency(currencyList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {t -> println(t.printStackTrace()) }).let { compositeDisposable.add(it) }
    }
}