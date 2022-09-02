package com.kaskin.manager.Views.Activities.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaskin.manager.Models.LoggedInUserView

class HomeViewModel : ViewModel() {


    private val _user = MutableLiveData<LoggedInUserView>().apply {
        value = LoggedInUserView(" ", " ")
    }
    val user: LiveData<LoggedInUserView> = _user

    fun UpdateUser(userView: LoggedInUserView) {
        _user.value = userView
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}