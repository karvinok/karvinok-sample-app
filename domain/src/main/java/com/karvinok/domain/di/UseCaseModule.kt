package com.karvinok.domain.di

import com.karvinok.domain.uc.HomeUC
import org.koin.core.module.Module
import org.koin.dsl.module

object UseCaseModule : KoinModuleProvider {
    override fun get() = module {
        single { HomeUC(get()) }
    }
}