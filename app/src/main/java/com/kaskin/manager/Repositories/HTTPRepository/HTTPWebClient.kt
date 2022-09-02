package com.kaskin.manager.Repositories.HTTPRepository

import com.kaskin.manager.Repositories.HTTPRepository.Contracts.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HTTPWebClient {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl("http://srvksk.dyndns.org:7383")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun loginService(): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}