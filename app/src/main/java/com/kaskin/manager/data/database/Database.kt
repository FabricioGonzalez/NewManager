package com.kaskin.manager.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.kaskin.manager.data.database.client.DAO.ClientDAO
import com.kaskin.manager.data.database.client.model.ClientDto
import com.kaskin.manager.data.database.employee.DAO.EmployeeDAO
import com.kaskin.manager.data.database.employee.model.EmployeeDTO
import com.kaskin.manager.data.database.week.DAO.WeekDAO
import com.kaskin.manager.data.database.week.model.WeekDTO

@Database(
    entities = [EmployeeDTO::class, WeekDTO::class, ClientDto::class],
    version = 65,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 59),
        AutoMigration(from = 59, to = 60),
        AutoMigration(from = 60, to = 61),
        AutoMigration(from = 61, to = 62),
        AutoMigration(from = 62, to = 63),
        AutoMigration(from = 63, to = 64),
        AutoMigration(from = 64, to = 65, spec = RenameDTACADCLTColumn::class),
        /*AutoMigration(from = 59, to = 2)*/
    ]
)
abstract class Database : RoomDatabase() {
    abstract val employeeDao: EmployeeDAO
    abstract val weekDao: WeekDAO
    abstract val clientDao: ClientDAO
}

@RenameColumn(
    tableName = "IMOBCLT",
    fromColumnName = "DTACADCLT",
    toColumnName = "DTACADCLT"
)
class RenameDTACADCLTColumn : AutoMigrationSpec

