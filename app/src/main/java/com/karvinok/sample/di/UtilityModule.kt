package com.karvinok.sample.di

import com.karvinok.domain.di.KoinModuleProvider
import com.karvinok.sample.utils.RxBus
import org.koin.dsl.module

object UtilityModule : KoinModuleProvider {
    override fun get() = module {
        single { RxBus() }
    }
}