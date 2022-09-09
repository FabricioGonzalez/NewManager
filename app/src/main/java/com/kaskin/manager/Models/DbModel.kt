package com.kaskin.manager.Models

import com.kaskin.manager.Enums.DbState
import java.time.LocalDate

data class DbModel(val dbname: String, val CreationData: LocalDate, val size: Float, val State: DbState)