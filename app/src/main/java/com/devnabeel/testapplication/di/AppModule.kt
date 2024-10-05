package com.devnabeel.testapplication.di

import com.devnabeel.testapplication.data.remote.ApiService
import com.devnabeel.testapplication.data.repository.TaskRepositoryImpl
import com.devnabeel.testapplication.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://demo1414406.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(apiService: ApiService): TaskRepository {
        return TaskRepositoryImpl(apiService)
    }
}
