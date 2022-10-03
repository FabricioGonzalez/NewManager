package com.kaskin.manager.presentation.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.kaskin.manager.Models.LoggedInUserView
import com.kaskin.manager.data.database.employee.EmployeeDatabase
import com.kaskin.manager.domain.employee.Employee
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _user = MutableLiveData<LoggedInUserView>().apply {
        value = LoggedInUserView(" ", " ")
    }
    val user: LiveData<LoggedInUserView> = _user

    private val _employee = MutableLiveData<Employee>().apply {
        value = Employee(0, "")
    }
    val employee: LiveData<Employee> = _employee

    fun GetEmployeeData(database: RoomDatabase) {
        viewModelScope.launch {
            val db = database as EmployeeDatabase
            var result = db.dao.getEmplyeeData()
            _employee.value = Employee(result.idFuncionario, result.apelidoFuncionario)
        }
    }

    fun UpdateUser(userView: LoggedInUserView) {
        _user.value = userView
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}