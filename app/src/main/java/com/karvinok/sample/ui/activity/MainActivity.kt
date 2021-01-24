package com.karvinok.sample.ui.activity

import androidx.viewbinding.ViewBinding
import com.karvinok.sample.R
import com.karvinok.sample.common.BaseActivity
import com.karvinok.sample.common.BaseViewModel
import com.karvinok.sample.common.viewBinding
import com.karvinok.sample.databinding.ActivityMainBinding
import com.karvinok.sample.ui.viewModel.MainVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    override val binding: ViewBinding by viewBinding(ActivityMainBinding::inflate)
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
