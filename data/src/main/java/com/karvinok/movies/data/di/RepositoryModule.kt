package com.karvinok.movies.data.di

import com.karvinok.domain.repository.HomeRepository
import com.karvinok.domain.di.KoinModuleProvider
import com.karvinok.movies.data.server.HomeServer
import org.koin.dsl.module

object RepositoryModule : KoinModuleProvider {
    override fun get() = module {
        single<HomeRepository> { HomeServer(get()) }
    }
}