package com.kaskin.manager.domain.login.repository

import com.kaskin.manager.domain.login.entities.User
import com.kaskin.manager.utils.Resource

interface LoginRepository {
    suspend fun login(username: String, password: String): Resource<User>
    suspend fun logout()
}