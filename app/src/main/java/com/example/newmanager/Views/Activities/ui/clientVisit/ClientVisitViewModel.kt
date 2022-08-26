package com.example.newmanager.Views.Activities.ui.clientVisit

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ClientVisitViewModel : ViewModel() {

    fun UpdateDay(day: String) {
        _text.value = "This is dia $day Fragment"
    }

    private val _text = MutableLiveData<String>().apply {
        value ="This is dia empty Fragment"
    }

    val text: LiveData<String> = _text
}