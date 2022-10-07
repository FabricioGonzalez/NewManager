package com.kaskin.manager.presentation.home.visitList.clientVisit

import android.util.Log
import androidx.lifecycle.*
import com.kaskin.manager.domain.clients.entities.Client
import com.kaskin.manager.domain.clients.usecases.GetAllClientsUsecase
import com.kaskin.manager.utils.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ClientVisitViewModel @AssistedInject constructor(
    private val getAllClientsUsecase: GetAllClientsUsecase,
    @Assisted("day") private val day: Int,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("day") day: Int,
        ): ClientVisitViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            day: Int,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(day) as T
            }
        }
    }

    fun changeDay() {
        _text.value = "This is dia $day"
    }

    private val _clients =
        MutableStateFlow<Resource<List<Client>>>(Resource.Loading())
    val clients: StateFlow<Resource<List<Client>>> = _clients

    private val _text = MutableLiveData<String>().apply {
        value = "This is dia $day"
    }
    val text: LiveData<String> = _text

    fun getClients(codigo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getAllClientsUsecase.let {
                it.invoke(codigo, day).catch { e ->
                    _clients.emit(
                        Resource.Error(
                            message = e.localizedMessage ?: "Error Unexpected"
                        )
                    )
                    Log.e("AppError", "getAllClientsUsecase: ${e.message}")
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
                            Log.e("AppError", "getAllClientsUsecase: ${result.message}")
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