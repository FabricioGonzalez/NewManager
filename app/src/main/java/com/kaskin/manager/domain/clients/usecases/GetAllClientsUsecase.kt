package com.kaskin.manager.domain.clients.usecases

import android.util.Log
import com.kaskin.manager.domain.clients.entities.Client
import com.kaskin.manager.domain.clients.repository.ClientRepository
import com.kaskin.manager.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllClientsUsecase @Inject constructor(
    private val repository: ClientRepository
) {
    operator fun invoke(codigoVendedor: Int, visita: Int): Flow<Resource<List<Client>>> = flow {
        emit(Resource.Loading<List<Client>>())

        val result = repository.getAllClientsByVisitDay(codigo = codigoVendedor, visitDay = visita)
        when (result) {
            is Resource.Error<List<Client>>
            -> {
                emit(Resource.Error<List<Client>>(result.message, result?.data))
                Log.e("GetAllClientsUsecase", "invoke: ${result.message}")
            }
            is Resource.Success<List<Client>>
            -> {
                emit(Resource.Success<List<Client>>(result.data))
            }
            is Resource.Loading<List<Client>>
            -> {
                emit(Resource.Loading<List<Client>>())
            }
        }

    }.catch { err ->
        emit(
            Resource.Error<List<Client>>(
                err.localizedMessage ?: "An unexpected Error occurred!"
            )
        )
    }
}