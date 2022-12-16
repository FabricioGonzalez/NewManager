package com.kaskin.manager.data.database.client.repository

import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.Database
import com.kaskin.manager.data.database.client.model.ToDto
import com.kaskin.manager.data.database.client.model.ToEntity
import com.kaskin.manager.domain.clients.entities.Client
import com.kaskin.manager.domain.clients.repository.ClientRepository
import com.kaskin.manager.utils.Resource
import javax.inject.Inject

class RoomClientRepository @Inject constructor(private val db: RoomDatabase) : ClientRepository {
    override suspend fun insertClient(client: Client) {
        (db as Database).clientDao.insertClient(client.ToDto())
    }

    override suspend fun getClientByVisitDay(codigo: Int, visitDay: Int): Resource<Client> {
        val clients =
            (db as Database).clientDao.getClientByVisitDay(codigo, visitDay).ToEntity()
        if (clients != null) {
            return Resource.Success(clients)
        }
        return Resource.Error(message = "Nada foi encontrado")
    }

    override suspend fun getClientByCode(codigo: Int): Resource<Client> {
        val client =
            (db as Database).clientDao.getClientByCode(codigo).ToEntity()
        if (client != null) {
            return Resource.Success(client)
        }
        return Resource.Error(message = "Nada foi encontrado")
    }

    override suspend fun getAllClientsByVendedor(codigo: Int): Resource<List<Client>> {
        val clients =
            (db as Database).clientDao.getAllClientsByVendedor(codigo).map { client ->
                client.ToEntity()
            }
        if (clients != null && clients.isNotEmpty()) {
            return Resource.Success(clients)
        }
        return Resource.Error(message = "Nada foi encontrado")
    }

    override suspend fun getAllClients(): Resource<List<Client>> {
        val clients =
            (db as Database).clientDao.getAllClients().map { client ->
                client.ToEntity()
            }
        if (clients != null && clients.isNotEmpty()) {
            return Resource.Success(clients)
        }
        return Resource.Error(message = "Nada foi encontrado")
    }

    override suspend fun getAllClientsByCode(codigo: Int): Resource<List<Client>> {
        val clients =
            (db as Database).clientDao.getAllClientsByCode(codigo).map { client ->
                client.ToEntity()
            }
        if (clients != null && clients.isNotEmpty()) {
            return Resource.Success(clients)
        }
        return Resource.Error(message = "Nada foi encontrado")
    }

    override suspend fun getAllClientsByVisitDay(
        codigo: Int,
        visitDay: Int
    ): Resource<List<Client>> {
        val clients = (if (visitDay == 0)
            (db as Database).clientDao.getAllClients()
        else
            (db as Database).clientDao.getAllClientsByVisitDay(/*codigo, */visitDay))
            .map { client ->
                client.ToEntity()
            }
        if (clients != null && clients.isNotEmpty()) {
            return Resource.Success(clients)
        }
        return Resource.Error(message = "Nada foi encontrado")
    }
}