package com.kaskin.manager.Repositories.HTTPRepository

import com.kaskin.manager.Repositories.HTTPRepository.Contracts.DowloadService
import com.kaskin.manager.Repositories.HTTPRepository.Contracts.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HTTPWebClient {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl("http://574a0458b586.sn.mynetname.net:7383")
        /*.baseUrl("https://192.168.1.176:7132/v1/login")*/
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun loginService(): LoginService {
        return retrofit.create(LoginService::class.java)
    }
    fun downloadService():DowloadService {
        return retrofit.create(DowloadService::class.java)
    }

}