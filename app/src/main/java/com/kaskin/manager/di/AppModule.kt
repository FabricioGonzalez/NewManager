package com.kaskin.manager.di

import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOKHTTPClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .connectionSpecs(
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) listOf(
                    ConnectionSpec.COMPATIBLE_TLS,
                    ConnectionSpec.CLEARTEXT
                )
                else listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)
            )
            .build()
    }
}