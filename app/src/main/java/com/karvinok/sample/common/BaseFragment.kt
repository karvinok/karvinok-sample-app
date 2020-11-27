package com.karvinok.sample.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.karvinok.sample.BR
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

abstract class BaseFragment<B: ViewDataBinding>: Fragment(), ViewInitializer, DisposableProcessor{
    protected lateinit var binding : B
    protected abstract val viewModel : BaseViewModel

    protected var dp = 1f

    override val disposable = CompositeDisposable()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
        performDataBinding()
        initViews()

        subscribeUI()
        subscribeBaseUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, createLayout(), container, false)
        return binding.root
    }

    private fun performDataBinding() {
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        dp = resources.displayMetrics.density
    }


    fun subscribeBaseUI(){
        addDispose {
            viewModel.uiEvent.subscribe { event ->
                try {
                    activity?.runOnUiThread {
                        when(event){
                            is BaseViewModel.CloseScreenEvent -> popBackStack()
                            else -> handleUIEvent(event)
                        }
                    }
                } catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline function: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner) { it?.let { function.invoke(it) } }
    }

    inline fun <T> Fragment.observeNullable(liveData: LiveData<T>, crossinline function: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner) { function.invoke(it) }
    }

    open fun popBackStack() {
        findNavController().popBackStack()
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