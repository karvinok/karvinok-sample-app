package com.karvinok.movies.ui.activity

import com.karvinok.movies.R
import com.karvinok.movies.common.BaseActivity
import com.karvinok.movies.common.BaseViewModel
import com.karvinok.movies.databinding.ActivityMainBinding
import com.karvinok.movies.ui.viewModel.MainVM
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
