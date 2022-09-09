package com.kaskin.manager.DataSources

import android.util.Log
import com.kaskin.manager.Models.*
import com.kaskin.manager.Repositories.HTTPRepository.HTTPWebClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            var responseData: UserLoginData? = null

            val call = HTTPWebClient().loginService()
                .GetLogin(LoginRequest(username, password))
            call.enqueue(object : Callback<LoginResponse?> {
                override fun onResponse(
                    call: Call<LoginResponse?>?,
                    response: Response<LoginResponse?>?
                ) {
                    responseData = response?.body()?.user
                }

                override fun onFailure(
                    call: Call<LoginResponse?>?,
                    t: Throwable?
                ) {
                    Log.e("onFailure error", t?.message.toString())
                }
            }
            )

            if (responseData != null) {

                val fakeUser =
                    LoggedInUser(responseData?.setor.toString(), responseData?.name.toString())
                return Result.Success(fakeUser)
            }
            return Result.Error(IOException("Error logging in"))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}