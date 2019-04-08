package com.example.exchangerates.data.remote.dto

data class CurrencyResponse(
    val Cur_ID: Int,
    val Date: String,
    val Cur_Abbreviation: String,
    val Cur_Scale: Int,
    val Cur_Name: String,
    val Cur_OfficialRate: Double
)