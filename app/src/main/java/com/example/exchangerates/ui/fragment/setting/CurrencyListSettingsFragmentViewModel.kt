package com.example.exchangerates.ui.fragment.setting

import com.example.exchangerates.data.interactor.CurrencyInteractor
import com.example.exchangerates.ui.base.BaseViewModel
import javax.inject.Inject

class CurrencyListSettingsFragmentViewModel @Inject constructor(private val currencyInteractor: CurrencyInteractor): BaseViewModel() {
}