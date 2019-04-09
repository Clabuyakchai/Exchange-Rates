package com.example.exchangerates.di.module

import android.content.Context
import androidx.room.Room
import com.example.exchangerates.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db").build()
    }

    @Singleton
    @Provides
    fun provideScheduleDao(appDatabase: AppDatabase) = appDatabase.currencyDao()
}