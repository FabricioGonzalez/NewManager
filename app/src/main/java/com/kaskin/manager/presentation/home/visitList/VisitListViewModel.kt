package com.kaskin.manager.presentation.home.visitList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaskin.manager.domain.week.entities.WeekDay
import com.kaskin.manager.domain.week.usecases.GetWeekDaysUsecase
import com.kaskin.manager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VisitListViewModel @Inject constructor(
    private val getWeekDaysUsecase: GetWeekDaysUsecase
) : ViewModel() {

    private val _weekDays =
        MutableStateFlow<Resource<List<WeekDay>>>(Resource.Loading())

    val weekDays: StateFlow<Resource<List<WeekDay>>> = _weekDays

    fun getWeekDays() {
        viewModelScope.launch(Dispatchers.IO) {
            getWeekDaysUsecase().catch { e ->
                _weekDays.emit(
                    Resource.Error(
                        message = e.localizedMessage ?: "Error Unexpected"
                    )
                )
            }.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _weekDays.emit(Resource.Loading())
                    }
                    is Resource.Error -> {
                        _weekDays.emit(
                            Resource.Error(
                                data = result.data,
                                message = result.message
                            )
                        )
                    }
                    is Resource.Success -> {
                        _weekDays.emit(Resource.Success(data = result.data))
                    }
                }
            }
        }
    }
}