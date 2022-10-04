package com.kaskin.manager.data.database.week.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaskin.manager.data.database.week.model.WeekDTO

@Dao
interface WeekDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeekDay(weekDay: WeekDTO)

    @Query("SELECT * FROM IMOBVST")
    suspend fun getWeekDays(): List<WeekDTO>
}