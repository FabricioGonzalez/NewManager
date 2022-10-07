package com.kaskin.manager.domain.clients.repository

import com.kaskin.manager.domain.clients.entities.Client
import com.kaskin.manager.utils.Resource

interface ClientRepository {
    suspend fun insertClient(client: Client)
    suspend fun getAllClientsByVendedor(codigo: Int): Resource<List<Client>>
    suspend fun getAllClients(): Resource<List<Client>>
    suspend fun getAllClientsByVisitDay(codigo: Int, visitDay: Int): Resource<List<Client>>
    suspend fun getAllClientsByCode(codigo: Int): Resource<List<Client>>
    suspend fun getClientByVisitDay(codigo: Int, visitDay: Int): Resource<Client>
    suspend fun getClientByCode(codigo: Int): Resource<Client>
}