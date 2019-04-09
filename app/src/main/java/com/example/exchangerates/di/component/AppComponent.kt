package com.example.exchangerates.di.component

import com.example.exchangerates.App
import com.example.exchangerates.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        RemoteModule::class,
        ActivityModule::class,
        ViewModelsModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ApplicationModule::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}