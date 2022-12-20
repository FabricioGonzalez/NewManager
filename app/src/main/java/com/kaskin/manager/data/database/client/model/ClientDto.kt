package com.kaskin.manager.data.database.client.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kaskin.manager.domain.clients.entities.Client

@Entity(tableName = "IMOBCLT")
data class ClientDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "CODCLT", defaultValue = "0")
    val codigoCliente: Int,
    @ColumnInfo(name = "NOMRAZSCLCLT")
    val razaoSocial: String,
    @ColumnInfo(name = "NOMFTSCLT")
    val nomeFantasia: String?,
    @ColumnInfo(name = "NUMCPFCLT")
    val cpf: String?,
    @ColumnInfo(name = "NUMCGCCLT")
    val cnpj: String?,
    @ColumnInfo(name = "NUMICCETACLT")
    val ie: String?,
    @ColumnInfo(name = "VALSLDCLT", defaultValue = "0.0")
    val valorSaldo: Double?,
    @ColumnInfo(name = "VALLMTCDTCLT", defaultValue = "0.0")
    val limiteCrédito: Double?,
    @ColumnInfo(name = "DATCADCLT")
    val dataCadastro: String?,
    @ColumnInfo(name = "BOLBLTCLT", defaultValue = "0")
    val BOLBLTCLT: Int?,
    @ColumnInfo(name = "EMLCLT")
    val EMLCLT: String?,
    @ColumnInfo(name = "LATCLT", defaultValue = "0.0")
    val latitude: Double?,
    @ColumnInfo(name = "LONCLT", defaultValue = "0.0")
    val longitude: Double?,
    @ColumnInfo(name = "CLTLCK", defaultValue = "0")
    val CLTLCK: Int?,
    @ColumnInfo(name = "CODTRT", defaultValue = "0")
    val CODTRT: Int?,
    @ColumnInfo(name = "CODSTR", defaultValue = "0")
    val CODSTR: Int?,
    @ColumnInfo(name = "CODROT", defaultValue = "0")
    val CODROT: Int?,
    @ColumnInfo(name = "CODCTICLT", defaultValue = "0")
    val CODCTICLT: Int?,
    @ColumnInfo(name = "CODTBLPRCCLT", defaultValue = "0")
    val tabelaPreco: Int?,
    @ColumnInfo(name = "CODRTOCLT", defaultValue = "0")
    val CODRTOCLT: Int?,
    @ColumnInfo(name = "BOLFRZPPR", defaultValue = "0")
    val BOLFRZPPR: Int?,
    @ColumnInfo(name = "VSTCLT", defaultValue = "0")
    val VSTCLT: Int?,
    @ColumnInfo(name = "SEQVST", defaultValue = "0")
    val SEQVST: Int?,
    @ColumnInfo(name = "CODFRMPAGPDR", defaultValue = "0")
    val CODFRMPAGPDR: Int?,
    @ColumnInfo(name = "DESOBSCLT")
    val DESOBSCLT: String?,
    @ColumnInfo(name = "CODEPGRSPCAD", defaultValue = "0")
    val CODEPGRSPCAD: Int?,
    @ColumnInfo(name = "DATITVCLT")
    val DATITVCLT: String?,
    @ColumnInfo(name = "DESEDRCLT")
    val DESEDRCLT: String?,
    @ColumnInfo(name = "NUMEDRCLT")
    val NUMEDRCLT: String?,
    @ColumnInfo(name = "DESBRO")
    val DESBRO: String?,
    @ColumnInfo(name = "DESCDD")
    val DESCDD: String?,
    @ColumnInfo(name = "CODUF_")
    val CODUF: String?,
    @ColumnInfo(name = "NUMCEPEDRCLT")
    val NUMCEPEDRCLT: String?,
    @ColumnInfo(name = "QTDPTR", defaultValue = "0")
    val QTDPTR: Int?,
    @ColumnInfo(name = "GROMEDMES", defaultValue = "0.0")
    val GROMEDMES: Double?,
    @ColumnInfo(name = "GROMESTRI", defaultValue = "0.0")
    val GROMESTRI: Double?,
    @ColumnInfo(name = "BOLINDCLTPOS")
    val BOLINDCLTPOS: String?,
    @ColumnInfo(name = "BOLCLTAFD", defaultValue = "0")
    val BOLCLTAFD: Int,
    @ColumnInfo(name = "BOLINDCLTIBN", defaultValue = "0")
    val BOLINDCLTIBN: Int,
    @ColumnInfo(name = "BOLVNDLCK", defaultValue = "0")
    val BOLVNDLCK: Int,
    @ColumnInfo(name = "DESMSGVNDLCK")
    val DESMSGVNDLCK: String?,
    @ColumnInfo(name = "PDDSYNC", defaultValue = "0")
    val PDDSYNC: Int,
    @ColumnInfo(name = "GIRO", defaultValue = "0")
    val GIRO: Double,
    @ColumnInfo(name = "FREEZER", defaultValue = "0")
    val FREEZER: Int,
    @ColumnInfo(name = "CODRTBCLT", defaultValue = "1")
    val CODRTBCLT: Int,
    @ColumnInfo(name = "VALMINPDD", defaultValue = "0.0")
    val VALMINPDD: Double,
    @ColumnInfo(name = "CLTFUEL", defaultValue = "0")
    val CLTFUEL: Int,
    @ColumnInfo(name = "VALSLDTRC", defaultValue = "0.0")
    val VALSLDTRC: Double,
    @ColumnInfo(name = "FISULTSIT")
    val FISULTSIT: String?,
    @ColumnInfo(name = "FISBXA")
    val FISBXA: String?,
    @ColumnInfo(name = "FISCLTHAB", defaultValue = "0")
    val FISCLTHAB: Int,
    @ColumnInfo(name = "CODVDD", defaultValue = "0")
    val CODVDD: Int
)

fun ClientDto.ToEntity(): Client {
    return Client(
        codigoCliente = codigoCliente,
        razaoSocial = razaoSocial,
        nomeFantasia = nomeFantasia!!,
        CPF = cpf,
        CNPJ = cnpj,
        IE = ie,
        saldo = valorSaldo,
        limiteCredito = limiteCrédito,
        setor = CODVDD
    )
}

fun Client.ToDto(): ClientDto {
    return ClientDto(
        codigoCliente = codigoCliente,
        razaoSocial = razaoSocial,
        nomeFantasia = nomeFantasia,
        cpf = CPF,
        cnpj = CNPJ,
        ie = IE,
        valorSaldo = saldo,
        limiteCrédito = limiteCredito,
        id = setor,
        BOLBLTCLT = 0,
        BOLCLTAFD = 0,
        BOLFRZPPR = 0,
        BOLINDCLTIBN = 0,
        BOLINDCLTPOS = "",
        BOLVNDLCK = 0,
        CLTFUEL = 0,
        CLTLCK = 0,
        CODCTICLT = 0,
        CODEPGRSPCAD = 0,
        CODFRMPAGPDR = 0,
        CODROT = 0,
        CODRTBCLT = 0,
        CODRTOCLT = 0,
        CODSTR = 0,
        CODTRT = 0,
        CODUF = "",
        dataCadastro = "",
        DATITVCLT = "",
        DESBRO = "",
        DESCDD = "",
        DESEDRCLT = "",
        DESMSGVNDLCK = "",
        DESOBSCLT = "",
        EMLCLT = "",
        FISBXA = "",
        FISCLTHAB = 0,
        FISULTSIT = "",
        FREEZER = 0,
        GIRO = 0.0,
        GROMEDMES = 0.0,
        GROMESTRI = 0.0,
        latitude = 0.0,
        longitude = 0.0,
        NUMCEPEDRCLT = "",
        NUMEDRCLT = "",
        PDDSYNC = 0,
        QTDPTR = 0,
        SEQVST = 0,
        tabelaPreco = 0,
        VALMINPDD = 0.0,
        VALSLDTRC = 0.0,
        VSTCLT = 0,
        CODVDD = setor!!
    )
}
