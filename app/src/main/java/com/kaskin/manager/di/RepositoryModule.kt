package com.kaskin.manager.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaskin.manager.data.database.Database
import com.kaskin.manager.data.database.employee.repository.RoomEmployeeRepository
import com.kaskin.manager.data.database.week.repository.RoomWeekRepository
import com.kaskin.manager.data.remote.login.repositories.LoginRepositoryImpl
import com.kaskin.manager.data.remote.login.services.LoginService
import com.kaskin.manager.domain.employee.repository.EmployeeRepository
import com.kaskin.manager.domain.login.repository.LoginRepository
import com.kaskin.manager.domain.week.repository.WeekRepository
import com.kaskin.manager.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    
    @Provides
    @ViewModelScoped
    fun provideLoginApi(client: OkHttpClient): LoginService {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
            .create(LoginService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "${context.getExternalFilesDir("databases").toString()}${File.separatorChar}manager.db"
        )
            /*.addMigrations(MIGRATION_1_2)*/.build()
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

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(service: LoginService): LoginRepository {
        return LoginRepositoryImpl(service)
    }
}