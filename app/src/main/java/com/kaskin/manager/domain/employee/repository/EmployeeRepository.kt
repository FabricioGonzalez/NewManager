package com.kaskin.manager.domain.employee.repository

import com.kaskin.manager.domain.employee.entities.Employee
import com.kaskin.manager.utils.Resource

interface EmployeeRepository {
    suspend fun insertEmployee(employee: Employee)
    suspend fun getEmplyeeData(): Resource<Employee>
}