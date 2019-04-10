package com.example.exchangerates.data.local.dao

import androidx.room.*
import com.example.exchangerates.data.local.entity.CurrencyEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyEntity: CurrencyEntity)

    @Update
    fun update(currencyEntity: CurrencyEntity)

    @Delete
    fun delete(currencyEntity: CurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(listCurrency: List<CurrencyEntity>)

    @Query("SELECT * FROM currency WHERE id = :id")
    fun getCurrencyById(id: Int): CurrencyEntity?

    @Query("SELECT * FROM currency ORDER BY position ASC")
    fun getAll(): Single<List<CurrencyEntity>>
}