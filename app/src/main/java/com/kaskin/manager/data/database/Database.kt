package com.kaskin.manager.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.employee.DAO.EmployeeDAO
import com.kaskin.manager.data.database.employee.model.EmployeeDTO
import com.kaskin.manager.data.database.week.DAO.WeekDAO
import com.kaskin.manager.data.database.week.model.WeekDTO

@Database(
    entities = [EmployeeDTO::class, WeekDTO::class],
    version = 61,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 59),
        AutoMigration(from = 59, to = 60),
       AutoMigration(from = 60, to = 61),
        /*AutoMigration(from = 59, to = 2)*/
    ]
)
abstract class Database : RoomDatabase() {
    abstract val employeeDao: EmployeeDAO
    abstract val weekDao: WeekDAO
}
