package com.kaskin.manager.presentation.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaskin.manager.Models.LoggedInUserView
import com.kaskin.manager.Models.LoginFormState
import com.kaskin.manager.Models.LoginResult
import com.kaskin.manager.Models.Result
import com.kaskin.manager.R
import com.kaskin.manager.Repositories.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        GlobalScope.launch(Dispatchers.IO) {
            val result = loginRepository.login(username, password)

            if (result is Result.Success) {
                _loginResult.postValue(
                    LoginResult(
                        success = LoggedInUserView(
                            displayName = result.data.displayName,
                            result.data.userId
                        )
                    )
                )
            }
            else {
                _loginResult.postValue(LoginResult(error = R.string.login_failed))
            }
        }

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 3
    }
}