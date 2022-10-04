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
        return try {
            val days =
                (db as Database).weekDao.getWeekDays().map { day ->
                    day.ToEntity()
                }
            if (days != null) {
                return Resource.Error<List<WeekDay>>(message = "Nada foi encontrado")
            }
            return Resource.Success<List<WeekDay>>(days)
        } catch (e: Exception) {
            return Resource.Error<List<WeekDay>>(e.localizedMessage ?: "Algo deu errado")
        }
    }
}