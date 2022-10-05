package com.kaskin.manager.domain.login.usecases

import com.kaskin.manager.domain.login.entities.User
import com.kaskin.manager.domain.login.repository.LoginRepository
import com.kaskin.manager.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogOutUsecase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(): Flow<Resource<User>> = flow {

    }
}