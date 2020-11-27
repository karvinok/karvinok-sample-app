package com.karvinok.sample.common

interface ViewInitializer {
    fun createLayout() : Int

    fun initData()
    fun initViews()
    fun subscribeUI()
    fun handleUIEvent(event: BaseViewModel.UIEvent)
}