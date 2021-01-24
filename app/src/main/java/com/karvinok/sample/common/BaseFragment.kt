package com.karvinok.sample.common

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.karvinok.sample.R
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

abstract class BaseFragment(@LayoutRes id : Int): Fragment(id),
    ViewInitializer, DisposableProcessor {

    protected abstract val viewModel : BaseViewModel
    protected abstract val binding: ViewBinding

    protected var dp = 1f

    override val disposable = CompositeDisposable()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
        initViews()
        initDpValue()
        subscribeUI()
        subscribeBaseUI()
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
        observe(viewModel.isLoading, ::showLoading)
    }

    inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline function: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner) { it?.let { function.invoke(it) } }
    }

    inline fun <T> Fragment.observeNullable(liveData: LiveData<T>, crossinline function: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner) { function.invoke(it) }
    }

    override fun initDpValue(){
        dp = resources.displayMetrics.density
    }

    open fun popBackStack() {
        findNavController().popBackStack()
    }

    open fun showLoading(isShow: Boolean){
        binding.root.findViewById<View>(R.id.view_progress)?.let {
            it.visibility = if (isShow) View.VISIBLE else View.GONE
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
        viewModel.onDestroy()
        disposable.clear()
    }

}