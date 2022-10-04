package com.kaskin.manager.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.Database
import com.kaskin.manager.data.database.employee.repository.RoomEmployeeRepository
import com.kaskin.manager.data.database.week.repository.RoomWeekRepository
import com.kaskin.manager.domain.employee.repository.EmployeeRepository
import com.kaskin.manager.domain.week.repository.WeekRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File

@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "${context.getExternalFilesDir("databases").toString()}${File.separatorChar}manager.db"
        )
            /*.addMigrations(MIGRATION_1_2)*/
            .build()
    }

    @Provides
    @ViewModelScoped
    fun provideEmployeeDatabase(db: RoomDatabase): EmployeeRepository {
        return RoomEmployeeRepository(db)
    }

    @Provides
    @ViewModelScoped
    fun provideWeekDatabase(db: RoomDatabase): WeekRepository {
        return RoomWeekRepository(db)
    }
}