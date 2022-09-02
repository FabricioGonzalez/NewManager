package com.kaskin.manager.Repositories.HTTPRepository.Contracts

import com.kaskin.manager.Models.LoginRequest
import com.kaskin.manager.Models.UserLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/AndroidDb/LoginMobile")
    fun GetLogin(@Body login: LoginRequest): Call<UserLoginData>
}