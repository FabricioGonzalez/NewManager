package com.kaskin.manager.domain.database.entities

import com.kaskin.manager.domain.database.enums.DbState
import java.time.LocalDate

data class DbModel(val dbname: String, val CreationData: LocalDate, val size: Float, val State: DbState)