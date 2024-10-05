package com.devnabeel.testapplication.domain.repository

import com.devnabeel.testapplication.domain.model.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
}