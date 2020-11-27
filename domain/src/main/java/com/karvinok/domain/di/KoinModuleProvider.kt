package com.karvinok.domain.di

import org.koin.core.module.Module

interface KoinModuleProvider {
    fun get(): Module
}