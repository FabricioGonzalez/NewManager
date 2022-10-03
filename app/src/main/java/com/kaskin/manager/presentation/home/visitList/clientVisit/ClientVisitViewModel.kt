package com.kaskin.manager.presentation.home.visitList.clientVisit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClientVisitViewModel : ViewModel() {

    fun UpdateDay(day: String) {
        _text.value = "This is dia $day Fragment"
    }

    private val _text = MutableLiveData<String>().apply {
        value ="This is dia empty Fragment"
    }

    val text: LiveData<String> = _text
}