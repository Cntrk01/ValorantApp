package com.movieapp.valorantapp.module

import com.movieapp.valorantapp.service.ValorantAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit=
        Retrofit.Builder()
            .baseUrl(ValorantAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : ValorantAPI=
        retrofit.create(ValorantAPI::class.java)
}