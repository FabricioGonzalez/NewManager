package com.kaskin.manager.data.database.employee

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.employee.model.Employee

@Database(
    entities = [Employee::class],
    version = 1
)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract val dao: EmployeeDAO
}