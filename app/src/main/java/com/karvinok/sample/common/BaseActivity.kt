package com.karvinok.sample.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.karvinok.sample.BR
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), ViewInitializer,
    DisposableProcessor {
    protected lateinit var binding: B
    protected abstract val viewModel: BaseViewModel

    protected var dp = Float.MAX_VALUE

    override val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initData()
        initViews()
        subscribeUI()
        subscribeBaseUI()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, createLayout())
        binding.apply {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = this@BaseActivity
            setContentView(root)
            executePendingBindings()
        }

        dp = resources.displayMetrics.density
    }


    fun subscribeBaseUI() {
        addDispose {
            viewModel.uiEvent.subscribe { event ->
                try {
                    runOnUiThread {
                        when (event) {
                            is BaseViewModel.CloseScreenEvent -> finish()
                            else -> handleUIEvent(event)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}