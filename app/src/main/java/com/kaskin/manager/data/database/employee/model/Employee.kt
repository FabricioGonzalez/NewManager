package com.kaskin.manager.data.database.employee.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IMOBEPG")
data class Employee(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "CODMTCEPG")
    val idFuncionario: Int,

    @ColumnInfo(name = "NOMEPG")
    val nomeFuncionario: String,

    @ColumnInfo(name = "DESAPLEPG")
    val apelidoFuncionario: String,

    @ColumnInfo(name = "NUMTLFEPG")
    val telefoneFuncionario: String,

    @ColumnInfo(name = "CODMTCEPGRPS")
    val CODMTCEPGRPS: Int,

    @ColumnInfo(name = "DESSVRFTP")
    val DESSVRFTP: String,

    @ColumnInfo(name = "DESUSRFTP")
    val DESUSRFTP: String,

    @ColumnInfo(name = "DESPWDFTP")
    val DESPWDFTP: String,

    @ColumnInfo(name = "DESSVRWEB")
    val DESSVRWEB: String,

    @ColumnInfo(name = "DESUSRWEB")
    val DESUSRWEB: String,

    @ColumnInfo(name = "DESPWDWEB")
    val DESPWDWEB: String,

    @ColumnInfo(name = "DESSVRPOP")
    val DESSVRPOP: String,

    @ColumnInfo(name = "DESSVRSMTP")
    val DESSVRSMTP: String,

    @ColumnInfo(name = "DESUSRPOP")
    val DESUSRPOP: String,

    @ColumnInfo(name = "DESPWDPO")
    val DESPWDPO: String,

    @ColumnInfo(name = "IGIRO")
    val IGIRO: Double
)
