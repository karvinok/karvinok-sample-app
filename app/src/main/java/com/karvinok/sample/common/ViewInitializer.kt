package com.karvinok.sample.common

interface ViewInitializer {

    fun initData()
    fun initViews()
    fun subscribeUI()
    fun initDpValue()
    fun handleUIEvent(event: BaseViewModel.UIEvent)
}