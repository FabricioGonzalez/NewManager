package com.kaskin.manager.domain.employee.usecases

import com.kaskin.manager.domain.employee.entities.Employee
import com.kaskin.manager.domain.employee.repository.EmployeeRepository
import com.kaskin.manager.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmployeeUsecase @Inject constructor(
    private val repository: EmployeeRepository
) {
    operator fun invoke(): Flow<Resource<Employee>> = flow {
        emit(Resource.Loading())

        when (val employee = repository.getEmplyeeData()) {
            is Resource.Error<Employee> -> {
                emit(Resource.Error(employee.message))
            }
            is Resource.Success<Employee> -> {
                emit(Resource.Success(employee.data))
            }
            is Resource.Loading<Employee> -> {
                emit(Resource.Loading())
            }
        }

    }.catch { err ->
        emit(
            Resource.Error(
                err.localizedMessage ?: "An unexpected Error occurred!"
            )
        )
    }
}