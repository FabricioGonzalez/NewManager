package com.kaskin.manager.Repositories.HTTPRepository.Contracts

import com.kaskin.manager.Models.LoginRequest
import com.kaskin.manager.Models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/v1/login")
    fun GetLogin(@Body login: LoginRequest): Call<LoginResponse>
}