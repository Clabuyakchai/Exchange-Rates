package com.example.exchangerates.util

import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

const val BASE_URL: String = "http://www.nbrb.by"

object DayHelper {
    val mutableFirstDay = MutableLiveData<String>()
    val mutableSecondDay = MutableLiveData<String>()

    val today: Date = Calendar.getInstance().time
    val tomorrow: Date = Calendar.getInstance()
        .apply {
            add(Calendar.DATE, 1)
        }.time

    val yesterday: Date = Calendar.getInstance()
        .apply {
            add(Calendar.DATE, -1)
        }.time


}

object DefaultCurrencyShow {
    val listDefaultCurrencyShow = listOf(
        "USD",
        "EUR",
        "RUB"
    )
}

fun Date.toApiFormat(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this)

fun Date.toUIFormat(): String = SimpleDateFormat("dd.MM.yy", Locale.US).format(this)