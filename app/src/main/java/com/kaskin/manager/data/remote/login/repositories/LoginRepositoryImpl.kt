package com.kaskin.manager.data.remote.login.repositories

import com.kaskin.manager.data.remote.login.services.LoginService
import com.kaskin.manager.domain.login.entities.User
import com.kaskin.manager.domain.login.repository.LoginRepository
import com.kaskin.manager.domain.week.enums.AppUserRole
import com.kaskin.manager.utils.Resource
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
           /* var responseData: UserLoginData? = repository
                .getLogin(LoginRequest(username, password)).execute().body()?.user

            if (responseData != null) {
*/
                return Resource.Success(
                    User(
                        userRole = AppUserRole.Administrator,
                        setor = username.toInt(),
                        name = "Ti"
                    )
                )
            /*}*/
         /*   return Resource.Error(IOException("Error logging in").localizedMessage)*/
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Unexpected error in Log in")
        }
    }

    suspend override fun logout() {
        // TODO: revoke authentication
    }
}