package com.karvinok.sample.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity(), ViewInitializer,
    DisposableProcessor {
    protected abstract val binding: ViewBinding
    protected abstract val viewModel: BaseViewModel

    protected var dp = Float.MAX_VALUE

    override val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        initViews()
        initDpValue()
        subscribeUI()
        subscribeBaseUI()
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

    override fun initDpValue(){
        dp = resources.displayMetrics.density
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