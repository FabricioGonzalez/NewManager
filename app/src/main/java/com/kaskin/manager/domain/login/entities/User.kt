package com.kaskin.manager.domain.login.entities

import com.kaskin.manager.Enums.AppUserRole

data class User(val userRole: AppUserRole, val setor: Int, val name: String)