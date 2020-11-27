package com.karvinok.movies.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karvinok.domain.common.BaseError
import com.karvinok.domain.entity.Employee
import com.karvinok.domain.uc.HomeUC
import com.karvinok.movies.common.BaseViewModel
import com.karvinok.movies.utils.RxBus

/**
 * @param rxBus can be used to send data to service,
 */
class HomeVM(
    private val uc: HomeUC,
    private val rxBus : RxBus
) : BaseViewModel() {

    private val _employees = MutableLiveData<List<Employee>>()
    private val _anotherData = MutableLiveData<Any?>()

    fun requestEmployees() {
        doOnBackground(true) {
            uc.requestEmployees().either({ it ->
                println("___Request failed")
                when (it) {
                    is BaseError.ServerError -> {
                        //process server error codes
                    }
                    is BaseError.NetworkError -> {
                        //process timeout etc
                    }
                }
            }) {
                println("___ Request is OK: $it")
                _employees.postValue(it)
            }
        }
    }

    val employees : LiveData<List<Employee>> get() = _employees
    val anotherData : LiveData<Any?> get() = _anotherData

    class SomeToastEvent(val text : String) : UIEvent
}
