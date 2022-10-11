package com.kaskin.manager.data.database.employee.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kaskin.manager.domain.employee.entities.Employee

@Entity(tableName = "IMOBEPG")
data class EmployeeDTO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "CODMTCEPG", defaultValue = "0")
    val idFuncionario: Int,

    @ColumnInfo(name = "NOMEPG")
    val nomeFuncionario: String,

    @ColumnInfo(name = "DESAPLEPG")
    val apelidoFuncionario: String?,

    @ColumnInfo(name = "NUMTLFEPG")
    val telefoneFuncionario: String?,

    @ColumnInfo(name = "NUMCELEPG")
    val celularFuncionario: String?,

    @ColumnInfo(name = "CODMTCEPGRPS", defaultValue = "0")
    val CODMTCEPGRPS: Int? = 0,

    @ColumnInfo(name = "DESSVRFTP")
    val DESSVRFTP: String?,

    @ColumnInfo(name = "DESUSRFTP")
    val DESUSRFTP: String?,

    @ColumnInfo(name = "DESPWDFTP")

    val DESPWDFTP: String?,

    @ColumnInfo(name = "DESSVRWEB")
    val DESSVRWEB: String?,

    @ColumnInfo(name = "DESUSRWEB")
    val DESUSRWEB: String?,

    @ColumnInfo(name = "DESPWDWEB")
    val DESPWDWEB: String?,

    @ColumnInfo(name = "DESSVRPOP")
    val DESSVRPOP: String?,

    @ColumnInfo(name = "DESSVRSMTP")
    val DESSVRSMTP: String?,

    @ColumnInfo(name = "DESUSRPOP")
    val DESUSRPOP: String?,

    @ColumnInfo(name = "DESPWDPO")
    val DESPWDPO: String?,

    @ColumnInfo(name = "IGIRO", defaultValue = "0")
    val IGIRO: Double
)

fun EmployeeDTO.ToEntity(): Employee? {

    return apelidoFuncionario?.let { Employee(setor = idFuncionario, nome = it) }
}

fun Employee.ToDto(): EmployeeDTO {
    return EmployeeDTO(
        idFuncionario = setor,
        apelidoFuncionario = nome,
        celularFuncionario = "",
        CODMTCEPGRPS = 0,
        DESPWDFTP = "",
        DESPWDPO = "",
        DESPWDWEB = "",
        DESSVRFTP = "",
        DESSVRPOP = "",
        DESSVRSMTP = "",
        DESSVRWEB = "",
        DESUSRFTP = "",
        DESUSRPOP = "",
        DESUSRWEB = "",
        id = 0,
        IGIRO = 0.0,
        nomeFuncionario = "",
        telefoneFuncionario = ""
    )
}
