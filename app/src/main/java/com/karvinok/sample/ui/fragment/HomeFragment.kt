package com.karvinok.sample.ui.fragment

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.karvinok.sample.R
import com.karvinok.sample.common.BaseFragment
import com.karvinok.sample.common.BaseViewModel
import com.karvinok.sample.common.viewBinding
import com.karvinok.sample.databinding.FragmentHomeBinding
import com.karvinok.sample.ui.adapter.HomeAdapter
import com.karvinok.sample.ui.viewModel.HomeVM
import com.karvinok.sample.ui.viewModel.MainVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val viewModel: HomeVM by viewModel()
    override val binding by viewBinding(FragmentHomeBinding::bind)
    val mainVM: MainVM by sharedViewModel()
    private lateinit var itemsAdapter: HomeAdapter

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

        observeNullable(viewModel.anotherData) {
            //it may be null
        }
    }

    override fun handleUIEvent(event: BaseViewModel.UIEvent) {
        when (event) {
            is HomeVM.SomeToastEvent -> {
                Toast.makeText(activity, event.text, Toast.LENGTH_LONG).show()
            }
        }
    }

}