package com.kaskin.manager.data.remote.login.repositories

import com.kaskin.manager.Enums.AppUserRole
import com.kaskin.manager.Models.LoginRequest
import com.kaskin.manager.Models.UserLoginData
import com.kaskin.manager.data.remote.login.services.LoginService
import com.kaskin.manager.domain.login.entities.User
import com.kaskin.manager.domain.login.repository.LoginRepository
import com.kaskin.manager.utils.Resource
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginRepositoryImpl @Inject constructor(
    private val repository: LoginService
) :
    LoginRepository {

    suspend override fun login(username: String, password: String): Resource<User> {
        try {
            var responseData: UserLoginData? = repository
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


                return Resource.Success<User>(
                    User(
                        userRole = AppUserRole.Administrator,
                        setor = responseData!!.setor,
                        name = responseData?.name.toString()
                    )
                )
            }
            return Resource.Error<User>(IOException("Error logging in").localizedMessage)
        } catch (e: Exception) {
            return Resource.Error<User>(e.localizedMessage ?: "Unexpected error in Log in")
        }
    }

    suspend override fun logout() {
        // TODO: revoke authentication
    }
}