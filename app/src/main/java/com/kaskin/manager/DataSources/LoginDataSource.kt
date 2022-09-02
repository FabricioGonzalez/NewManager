package com.kaskin.manager.DataSources

import com.google.gson.Gson
import com.kaskin.manager.Models.LoggedInUser
import com.kaskin.manager.Models.LoginRequest
import com.kaskin.manager.Models.Result
import com.kaskin.manager.Models.UserLoginData
import com.kaskin.manager.Repositories.HTTPRepository.HTTPWebClient
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {

            val gson = Gson()

            var responseData: UserLoginData? = null

            val call = HTTPWebClient().loginService()
                .GetLogin (LoginRequest(username, password))
            responseData = call.execute(/*object : Callback<UserLoginData?> {
                override fun onResponse(
                    call: Call<UserLoginData?>?,
                    response: Response<UserLoginData?>?
                ) {
                    responseData = response?.body()
                }

                override fun onFailure(
                    call: Call<UserLoginData?>?,
                    t: Throwable?
                ) {
                    Log.e("onFailure error", t?.message.toString())
                }
            }*/
            ).body()

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