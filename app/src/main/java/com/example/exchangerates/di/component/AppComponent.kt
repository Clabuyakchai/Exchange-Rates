package com.example.exchangerates.di.component

import com.example.exchangerates.App
import com.example.exchangerates.di.module.ActivityModule
import com.example.exchangerates.di.module.RemoteModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class,
            RemoteModule::class,
            ActivityModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Buider: AndroidInjector.Builder<App>()
}