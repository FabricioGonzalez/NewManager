package com.kaskin.manager.data.database.week.repository

import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.Database
import com.kaskin.manager.data.database.week.model.ToEntity
import com.kaskin.manager.domain.week.entities.WeekDay
import com.kaskin.manager.domain.week.repository.WeekRepository
import com.kaskin.manager.utils.Resource
import javax.inject.Inject

class RoomWeekRepository @Inject constructor(private val db: RoomDatabase) : WeekRepository {
    override suspend fun getWeekDays(): Resource<List<WeekDay>> {
        val days =
            (db as Database).weekDao.getWeekDays().map { day ->
                day.ToEntity()
            }
        if (days != null) {
            return Resource.Success(days)
        }
        return Resource.Error(message = "Nada foi encontrado")

    }
}