package com.kaskin.manager.domain.login.entities

import com.kaskin.manager.domain.week.enums.AppUserRole

data class User(val userRole: AppUserRole, val setor: Int, val name: String)