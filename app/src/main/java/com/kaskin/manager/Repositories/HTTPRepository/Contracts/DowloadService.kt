package com.kaskin.manager.Repositories.HTTPRepository.Contracts

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Streaming
import java.io.InputStream

interface DowloadService {
//On your api interface
    @POST("api/AndroidDb/GetDB")
    @Streaming
    fun GetDatabase(@Body setor: String): Call<InputStream>
}