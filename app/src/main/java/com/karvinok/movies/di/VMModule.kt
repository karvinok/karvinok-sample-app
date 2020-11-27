package com.karvinok.movies.di

import com.karvinok.domain.di.KoinModuleProvider
import com.karvinok.movies.ui.viewModel.HomeVM
import com.karvinok.movies.ui.viewModel.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VMModule : KoinModuleProvider {
    override fun get() = module {
        viewModel { MainVM() }
        viewModel { HomeVM(get(), get()) }
    }
}