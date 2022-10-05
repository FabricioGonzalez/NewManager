package com.kaskin.manager.domain.login.usecases

import android.util.Log
import com.kaskin.manager.domain.login.entities.User
import com.kaskin.manager.domain.login.repository.LoginRepository
import com.kaskin.manager.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LogInUsecase @Inject constructor(
    private val repository: LoginRepository
) {

    operator fun invoke(username: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading<User>())

        val result = repository.login(username = username, password = password)
        when (result) {
            is Resource.Error<User> -> {
                emit(Resource.Error<User>(result.message, result?.data))
                Log.e("LoginUsecase", "invoke: ${result.message}")
            }
            is Resource.Success<User> -> {
                emit(Resource.Success<User>(result.data))
            }
            is Resource.Loading<User> -> {
                emit(Resource.Loading<User>())
            }
        }

    }.catch { err ->
        emit(
            Resource.Error<User>(
                err.localizedMessage ?: "An unexpected Error occurred!"
            )
        )
    }
}