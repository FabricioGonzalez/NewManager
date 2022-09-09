package com.kaskin.manager.Repositories.HTTPRepository

import com.kaskin.manager.Repositories.HTTPRepository.Contracts.DowloadService
import com.kaskin.manager.Repositories.HTTPRepository.Contracts.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HTTPWebClient {
    private val retrofit = Retrofit
        .Builder()
        /*.baseUrl("http://srvksk.dyndns.org:7383")*/
        .baseUrl("http://192.168.1.192:5132")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun loginService(): LoginService {
        return retrofit.create(LoginService::class.java)
    }
    fun downloadService():DowloadService {
        return retrofit.create(DowloadService::class.java)
    }

}