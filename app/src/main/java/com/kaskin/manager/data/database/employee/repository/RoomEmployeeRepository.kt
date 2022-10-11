package com.kaskin.manager.data.database.employee.repository

import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.Database
import com.kaskin.manager.data.database.employee.model.ToDto
import com.kaskin.manager.data.database.employee.model.ToEntity
import com.kaskin.manager.domain.employee.entities.Employee
import com.kaskin.manager.domain.employee.repository.EmployeeRepository
import com.kaskin.manager.utils.Resource
import javax.inject.Inject

class RoomEmployeeRepository @Inject constructor(private val db: RoomDatabase) :
    EmployeeRepository {
    override suspend fun insertEmployee(employee: Employee) {
        (db as Database).employeeDao.insertEmployee(employeeDto = employee.ToDto())
    }

    override suspend fun getEmplyeeData(): Resource<Employee> {
        return try {
            val result = (db as Database).employeeDao.getEmployeeData()
            return result.let {
                Resource.Success<Employee>(it.ToEntity())
            }
        } catch (e: Exception) {
            return Resource.Error<Employee>(e.localizedMessage ?: "Algo deu errado")
        }
    }
}