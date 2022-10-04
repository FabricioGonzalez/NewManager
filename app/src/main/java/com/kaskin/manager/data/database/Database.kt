package com.kaskin.manager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.employee.DAO.EmployeeDAO
import com.kaskin.manager.data.database.employee.model.EmployeeDTO
import com.kaskin.manager.data.database.week.DAO.WeekDAO
import com.kaskin.manager.data.database.week.model.WeekDTO

@Database(
    entities = [EmployeeDTO::class, WeekDTO::class],
    version = 59,
    exportSchema = true
    /* autoMigrations = [
         AutoMigration(from = 1, to = 59)
     ]*/
)
abstract class Database : RoomDatabase() {
    abstract val employeeDao: EmployeeDAO
    abstract val weekDao: WeekDAO
}

/*i
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {

    }
}*/
