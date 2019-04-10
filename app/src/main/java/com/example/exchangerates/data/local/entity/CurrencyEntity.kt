package com.example.exchangerates.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyEntity(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "symbol")
    val symbol: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "first_rate")
    val firstRate: Double,

    @ColumnInfo(name = "second_rate")
    val secondRate: Double,

    @ColumnInfo(name = "scale")
    val scale: Int,

    @ColumnInfo(name = "show")
    var isShow: Boolean = false,

    @ColumnInfo(name = "position")
    var position: Int = 0,

    @ColumnInfo(name = "first_date")
    var firstDate: String,

    @ColumnInfo(name = "second_date")
    var secondDate: String
)