package com.kaskin.manager.domain.clients.entities

data class Client(
    val codigoCliente: Int,
    val razaoSocial: String,
    val nomeFantasia: String,
    val CPF: String?,
    val CNPJ: String?,
    val IE: String?,
    val saldo: Double?,
    val limiteCredito: Double?,
    val setor: Int?,
)
