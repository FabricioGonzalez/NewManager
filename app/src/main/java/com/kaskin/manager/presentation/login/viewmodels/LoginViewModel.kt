package com.kaskin.manager.presentation.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaskin.manager.Models.LoggedInUserView
import com.kaskin.manager.Models.LoginFormState
import com.kaskin.manager.Models.LoginResult
import com.kaskin.manager.R
import com.kaskin.manager.domain.login.entities.User
import com.kaskin.manager.domain.login.usecases.LogInUsecase
import com.kaskin.manager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUsecase: LogInUsecase
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            logInUsecase(username, password).collect { result ->
                when (result) {
                    is Resource.Error<User> -> {
                        _loginResult.postValue(
                            LoginResult(
                                success = null,
                                error = R.string.login_failed,
                                isLoading = false
                            )
                        )
                    }
                    is Resource.Success<User> -> {
                        _loginResult.postValue(
                            LoginResult(
                                success = LoggedInUserView(
                                    displayName = result.data!!.name,
                                    setor = result.data.setor.toString()
                                ), error = null,
                                isLoading = false
                            )
                        )
                    }
                    is Resource.Loading<User> -> {
                        _loginResult.postValue(
                            LoginResult(
                                error = null,
                                success = null,
                                isLoading = true
                            )
                        )
                    }
                }
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
        return password.length >= 5
    }
}