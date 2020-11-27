package com.karvinok.movies.di

import com.karvinok.domain.di.KoinModuleProvider
import com.karvinok.movies.utils.RxBus
import org.koin.dsl.module
import kotlin.math.sin

object UtilityModule : KoinModuleProvider {
    override fun get() = module {
        single { RxBus() }
    }
}