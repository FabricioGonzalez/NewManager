package com.kaskin.manager.domain.week.repository

import com.kaskin.manager.domain.week.entities.WeekDay
import com.kaskin.manager.utils.Resource

interface WeekRepository {
    suspend fun getWeekDays(): Resource<List<WeekDay>>
}