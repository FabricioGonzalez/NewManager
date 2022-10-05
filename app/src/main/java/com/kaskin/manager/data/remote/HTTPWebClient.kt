package com.kaskin.manager.data.remote

import com.kaskin.manager.data.remote.login.LoginService
import com.kaskin.manager.data.remote.mobile_database.DowloadService
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
    fun downloadService(): DowloadService {
        return retrofit.create(DowloadService::class.java)
    }

}