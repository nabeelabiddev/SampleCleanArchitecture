package com.devnabeel.testapplication.data.remote

import com.devnabeel.testapplication.data.model.TaskDto
import com.devnabeel.testapplication.data.model.TaskResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    suspend fun getTasks(): TaskResponse
}