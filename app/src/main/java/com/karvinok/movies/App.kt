package com.karvinok.movies

import android.app.Application
import com.karvinok.domain.di.UseCaseModule
import com.karvinok.movies.data.di.NetworkModule
import com.karvinok.movies.data.di.RepositoryModule
import com.karvinok.movies.di.ServiceModule
import com.karvinok.movies.di.UtilityModule

import com.karvinok.movies.di.VMModule
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