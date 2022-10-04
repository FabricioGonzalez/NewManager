package com.kaskin.manager.Models

import com.kaskin.manager.Enums.AppUserRole

data class UserLoginData(val userRole: AppUserRole, val setor: Int, val name: String, val password: String)