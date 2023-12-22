package com.example.saber.di

import com.example.saber.BuildConfig
import com.example.saber.data.repository.SaberRepository
import com.example.saber.data.repository.SaberRepositoryImpl
import com.example.saber.data.retrofit.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): SaberRepository{
        return SaberRepositoryImpl(apiService)
    }
}