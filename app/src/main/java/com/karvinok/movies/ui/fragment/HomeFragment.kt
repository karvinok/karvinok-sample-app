package com.karvinok.movies.ui.fragment

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.karvinok.movies.R
import com.karvinok.movies.common.BaseFragment
import com.karvinok.movies.common.BaseViewModel
import com.karvinok.movies.databinding.FragmentHomeBinding
import com.karvinok.movies.ui.adapter.HomeAdapter
import com.karvinok.movies.ui.viewModel.HomeVM
import com.karvinok.movies.ui.viewModel.MainVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun createLayout(): Int = R.layout.fragment_home
    override val viewModel: HomeVM by viewModel()
    val mainVM: MainVM by sharedViewModel()
    private lateinit var itemsAdapter : HomeAdapter

    override fun initData() {
        viewModel.requestEmployees()
    }

    override fun initViews() {

        itemsAdapter = HomeAdapter {
            Toast.makeText(activity, it.name, Toast.LENGTH_LONG).show()
        }

        binding.rvEmployees.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
    }


    /**
     * @see observe filters nullable values
     * @see observeNullable updates data as it is
     */
    override fun subscribeUI() {
        observe(viewModel.employees, itemsAdapter::update)
        observeNullable(viewModel.anotherData){
             //it may be null
        }
    }

    override fun handleUIEvent(event: BaseViewModel.UIEvent) {
        when(event){
            is HomeVM.SomeToastEvent -> { Toast.makeText(activity, event.text, Toast.LENGTH_LONG).show() }
        }
    }

}