package com.kaskin.manager.DataSources

import com.kaskin.manager.Models.LoggedInUser
import com.kaskin.manager.Models.LoginRequest
import com.kaskin.manager.Models.Result
import com.kaskin.manager.Models.UserLoginData
import com.kaskin.manager.data.remote.HTTPWebClient
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            var responseData: UserLoginData? = HTTPWebClient().loginService()
            .GetLogin(LoginRequest(username, password)).execute().body()?.user

            /*val call = HTTPWebClient().loginService()
                .GetLogin(LoginRequest(username, password))
            val response = call.enqueue(object : Callback<LoginResponse?> {
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
            )*/

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