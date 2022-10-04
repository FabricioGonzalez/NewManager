package com.kaskin.manager.data.database.employee.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaskin.manager.data.database.employee.model.EmployeeDTO

@Dao
interface EmployeeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employeeDto: EmployeeDTO)

    @Query("SELECT * FROM IMOBEPG")
    suspend fun getEmplyeeData(): EmployeeDTO
}