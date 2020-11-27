package com.karvinok.sample

import android.app.Application
import com.karvinok.domain.di.UseCaseModule
import com.karvinok.sample.data.di.NetworkModule
import com.karvinok.sample.data.di.RepositoryModule
import com.karvinok.sample.di.ServiceModule
import com.karvinok.sample.di.UtilityModule

import com.karvinok.sample.di.VMModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                VMModule.get(),
                ServiceModule.get(),
                NetworkModule.get(),
                RepositoryModule.get(),
                UseCaseModule.get(),
                UtilityModule.get()
            )
        }
    }
}