package com.example.exchangerates.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exchangerates.data.remote.dto.CurrencyResponse

@Entity(tableName = "currency")
class CurrencyEntity (

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
    var position: Int = 0
    ) {
        companion object {
            fun create(pair: Pair<CurrencyResponse, CurrencyResponse>) =
                CurrencyEntity(
                    pair.first.Cur_ID,
                    pair.first.Cur_Abbreviation,
                    pair.first.Cur_Name,
                    pair.first.Cur_OfficialRate,
                    pair.second.Cur_OfficialRate,
                    pair.first.Cur_Scale
                )
        }
}