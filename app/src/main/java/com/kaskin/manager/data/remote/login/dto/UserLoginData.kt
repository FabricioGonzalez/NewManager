package com.kaskin.manager.data.remote.login.dto

import com.kaskin.manager.domain.week.enums.AppUserRole

data class UserLoginData(val userRole: AppUserRole, val setor: Int, val name: String, val password: String)