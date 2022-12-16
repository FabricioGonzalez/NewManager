package com.kaskin.manager.presentation.vendas.visitList.clientVisit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaskin.manager.domain.clients.entities.Client
import com.kaskin.manager.domain.clients.usecases.GetAllClientsUsecase
import com.kaskin.manager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientVisitViewModel @Inject constructor(
    private val getAllClientsUsecase: GetAllClientsUsecase,
) : ViewModel() {

    private var day: Int = 0

    fun changeDay(day: Int) {
        this.day = day
    }

    private val _clients =
        MutableStateFlow<Resource<List<Client>>>(Resource.Loading())
    val clients: StateFlow<Resource<List<Client>>> = _clients

    private val _text = MutableLiveData<String>().apply {
        value = "This is dia $day"
    }
    val text: LiveData<String> = _text

    fun getClients(codigo: Int) {
        viewModelScope.launch {
            getAllClientsUsecase.let {
                it.invoke(codigo, day).catch { e ->
                    _clients.emit(
                        Resource.Error(
                            message = e.localizedMessage ?: "Error Unexpected"
                        )
                    )
                    Log.e("AppError", "getAllClientsUsecase Catch: ${e.message}")
                }.collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _clients.emit(Resource.Loading())
                        }
                        is Resource.Error -> {
                            _clients.emit(
                                Resource.Error(
                                    data = result.data,
                                    message = result.message
                                )
                            )
                            Log.e("AppError", "getAllClientsUsecase Reponse: ${result.message}")
                        }
                        is Resource.Success -> {
                            _clients.emit(Resource.Success(data = result.data))
                        }
                    }
                }
            }
        }


    }
}