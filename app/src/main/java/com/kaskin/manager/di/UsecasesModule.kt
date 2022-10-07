package com.kaskin.manager.di

import com.kaskin.manager.domain.clients.repository.ClientRepository
import com.kaskin.manager.domain.clients.usecases.GetAllClientsUsecase
import com.kaskin.manager.domain.employee.repository.EmployeeRepository
import com.kaskin.manager.domain.employee.usecases.GetEmployeeUsecase
import com.kaskin.manager.domain.login.repository.LoginRepository
import com.kaskin.manager.domain.login.usecases.LogInUsecase
import com.kaskin.manager.domain.login.usecases.LogOutUsecase
import com.kaskin.manager.domain.week.repository.WeekRepository
import com.kaskin.manager.domain.week.usecases.GetWeekDaysUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
object UsecasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetEmployeeUsecase(repository: EmployeeRepository): GetEmployeeUsecase {
        return GetEmployeeUsecase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideLogInUsecase(repository: LoginRepository): LogInUsecase {
        return LogInUsecase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideLogOutUsecase(repository: LoginRepository): LogOutUsecase {
        return LogOutUsecase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetWeekDaysUsecase(repository: WeekRepository): GetWeekDaysUsecase {
        return GetWeekDaysUsecase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetAllClientsUsecase(repository: ClientRepository): GetAllClientsUsecase {
        return GetAllClientsUsecase(repository)
    }

}