package com.kaskin.manager.data.remote.login

import com.kaskin.manager.Models.LoginRequest
import com.kaskin.manager.Models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/v1/login")
    fun GetLogin(@Body login: LoginRequest): Call<LoginResponse>
}