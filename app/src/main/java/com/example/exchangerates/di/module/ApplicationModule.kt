package com.example.exchangerates.di.module

import android.content.Context
import com.example.exchangerates.App
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApplicationModule {
    @Singleton
    @Binds
    abstract fun provideContext(app: App): Context
}