package com.kaskin.manager.domain.week.usecases

import com.kaskin.manager.domain.week.entities.WeekDay
import com.kaskin.manager.domain.week.repository.WeekRepository
import com.kaskin.manager.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeekDaysUsecase @Inject constructor(
    private val repository: WeekRepository
) {
    operator fun invoke(): Flow<Resource<List<WeekDay>>> = flow {
        emit(Resource.Loading())

        val days = repository.getWeekDays()

        when (days) {
            is Resource.Error<List<WeekDay>> -> {
                emit(Resource.Error(days.message))
                println(days.message)
            }
            is Resource.Success<List<WeekDay>> -> {
                emit(Resource.Success(days.data))
            }
            is Resource.Loading<List<WeekDay>>
            -> {
                emit(Resource.Loading())
            }
        }

    }.catch { err ->
        emit(
            Resource.Error<List<WeekDay>>(
                err.localizedMessage ?: "An unexpected Error occurred!"
            )
        )
    }

}