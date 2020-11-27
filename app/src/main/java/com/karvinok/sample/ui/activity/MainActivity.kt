package com.karvinok.sample.ui.activity

import com.karvinok.sample.R
import com.karvinok.sample.common.BaseActivity
import com.karvinok.sample.common.BaseViewModel
import com.karvinok.sample.databinding.ActivityMainBinding
import com.karvinok.sample.ui.viewModel.MainVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun createLayout() = R.layout.activity_main
    override val viewModel: MainVM by viewModel()

    override fun initData() {
    }

    override fun initViews() {
    }

    override fun subscribeUI() {

    }

    override fun handleUIEvent(event: BaseViewModel.UIEvent) {

    }

}
