package com.kaskin.manager.data.database.week.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kaskin.manager.domain.week.entities.WeekDay

@Entity(tableName = "IMOBVST")
data class WeekDTO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "CODVST")
    val dayNumber: Int,

    @ColumnInfo(name = "DESVST")
    val dayName: String?
)

fun WeekDTO.ToEntity(): WeekDay {
    return WeekDay(dayNumber = dayNumber, dayName = dayName!!)
}

fun WeekDay.ToDto(): WeekDTO {
    return WeekDTO(dayNumber = dayNumber, dayName = dayName, id = 0)
}
