package com.kaskin.manager.data.remote.login.services

import com.kaskin.manager.data.remote.login.dto.LoginRequest
import com.kaskin.manager.data.remote.login.dto.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/v1/login")
    fun getLogin(@Body login: LoginRequest): Call<LoginResponse>
}