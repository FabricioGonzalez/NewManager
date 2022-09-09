package com.kaskin.manager.Repositories

import android.util.Log
import com.kaskin.manager.Repositories.HTTPRepository.HTTPWebClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

public class FileRepositories {
    public suspend fun GetExternalStorageFiles() {

    }

    fun DownloadDb(setor: String): InputStream? {
        try {
            var responseData: InputStream? = null

            val call = HTTPWebClient().downloadService()
                .GetDatabase(setor)
            call.enqueue(object : Callback<InputStream?> {
                override fun onResponse(
                    call: Call<InputStream?>?,
                    response: Response<InputStream?>?
                ) {
                    responseData = response?.body()
                }

                override fun onFailure(
                    call: Call<InputStream?>?,
                    t: Throwable?
                ) {
                    Log.e("onFailure error", t?.message.toString())
                }
            }
            )

            if (responseData != null) {

                return responseData as InputStream;
            }
            return null
        } catch (e: Throwable) {
            return null
        }
    }
}