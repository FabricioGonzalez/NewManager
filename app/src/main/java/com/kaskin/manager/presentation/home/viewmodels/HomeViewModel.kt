package com.kaskin.manager.presentation.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaskin.manager.Models.LoggedInUserView
import com.kaskin.manager.domain.employee.entities.Employee
import com.kaskin.manager.domain.employee.usecases.GetEmployeeUsecase
import com.kaskin.manager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEmployee: GetEmployeeUsecase
) : ViewModel() {

    private val _user = MutableStateFlow<Resource<LoggedInUserView>>(Resource.Loading())
    val user: StateFlow<Resource<LoggedInUserView>> = _user

    private val _employee = MutableStateFlow<Resource<Employee>>(Resource.Loading<Employee>())
    val employee: StateFlow<Resource<Employee>> = _employee

    init {
        getEmployeeData()
    }

    private fun getEmployeeData() {
        viewModelScope.launch {
            getEmployee().catch { e ->
                _employee.value =
                    Resource.Error<Employee>(message = e.localizedMessage ?: "Error Unexpected")
            }.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _employee.value =
                            Resource.Error<Employee>(message = result.message, data = result.data)
                    }
                    is Resource.Success -> {
                        _employee.value =
                            Resource.Success<Employee>(data = result.data)
                    }
                    is Resource.Loading -> {
                        _employee.value =
                            Resource.Loading<Employee>()
                    }
                }
            }
        }
    }

    fun UpdateUser(userView: LoggedInUserView) {
        _user.value = Resource.Success(userView)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}