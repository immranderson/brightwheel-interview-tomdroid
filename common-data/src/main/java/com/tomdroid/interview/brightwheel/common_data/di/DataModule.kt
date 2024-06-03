package com.tomdroid.interview.brightwheel.common_data.di

import com.tomdroid.interview.brightwheel.common_data.remote.RepoService
import com.tomdroid.interview.brightwheel.common_data.remote.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideKotlinxSerializationConverter(): Converter.Factory {
        val contentType =  MediaType.get("application/json")
        return Json.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        converterFactory: Factory
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideRepoService(
        retrofit: Retrofit
    ): RepoService {
        return retrofit.create(RepoService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchService(
        retrofit: Retrofit
    ): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}