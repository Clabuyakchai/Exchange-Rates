package com.example.exchangerates.data.local.dao

import androidx.room.*
import com.example.exchangerates.data.local.entity.CurrencyEntity

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyEntity: CurrencyEntity)

    @Update
    fun update(currencyEntity: CurrencyEntity)

    @Delete
    fun delete(currencyEntity: CurrencyEntity)
}