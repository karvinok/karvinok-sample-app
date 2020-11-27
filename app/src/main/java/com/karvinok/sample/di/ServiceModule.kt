package com.karvinok.sample.di

import com.karvinok.domain.di.KoinModuleProvider
import org.koin.dsl.module

/**
 *  Provide services like firebase impl, gps locator
 */
object ServiceModule : KoinModuleProvider {

    override fun get() = module {
        //single {}
    }
}