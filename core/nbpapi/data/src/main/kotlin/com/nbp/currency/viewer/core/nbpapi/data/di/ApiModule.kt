package com.nbp.currency.viewer.core.nbpapi.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nbp.currency.viewer.core.nbpapi.data.NbpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideConverterFactory() = Json.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nbp.pl/")
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideNbpService(retrofit: Retrofit): NbpService = retrofit.create(NbpService::class.java)
}
