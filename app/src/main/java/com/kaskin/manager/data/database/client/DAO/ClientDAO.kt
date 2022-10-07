package com.kaskin.manager.data.database.client.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaskin.manager.data.database.client.model.ClientDto

@Dao
interface ClientDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: ClientDto)

    @Query("SELECT * FROM IMOBCLT WHERE CODCLT = (:codigo)")
    suspend fun getAllClientsByVendedor(codigo: Int): List<ClientDto>

    @Query("SELECT * FROM IMOBCLT")
    suspend fun getAllClients(): List<ClientDto>

    @Query("SELECT * FROM IMOBCLT WHERE CODCLT = (:codigo)")
    suspend fun getAllClientsByCode(codigo: Int): List<ClientDto>

    @Query("SELECT * FROM IMOBCLT WHERE CODCLT = (:codigo)")
    suspend fun getClientByCode(codigo: Int): ClientDto
    
    @Query("SELECT * FROM IMOBCLT WHERE CODCLT = (:codigo) AND VSTCLT = (:visitDay)")
    suspend fun getAllClientsByVisitDay(codigo: Int, visitDay: Int): List<ClientDto>

    @Query("SELECT * FROM IMOBCLT WHERE CODCLT = (:codigo) AND VSTCLT = (:visitDay)")
    suspend fun getClientByVisitDay(codigo: Int, visitDay: Int): ClientDto
}