package com.kaskin.manager.presentation.home.visitList.clientVisit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaskin.manager.domain.clients.entities.Client
import com.kaskin.manager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ClientVisitViewModel @Inject constructor(

) : ViewModel() {

    private var day: Int = 0

    fun changeDay(day: Int) {
        this.day = day
        _text.value = "This is dia $day"
    }

    private val _clients =
        MutableStateFlow<Resource<List<Client>>>(Resource.Loading<List<Client>>())
    val clients: StateFlow<Resource<List<Client>>> = _clients

    private val _text = MutableLiveData<String>().apply {
        value = "This is dia $day"
    }
    val text: LiveData<String> = _text
}