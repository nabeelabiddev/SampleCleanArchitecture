package com.devnabeel.testapplication.data.repository

import com.devnabeel.testapplication.data.remote.ApiService
import com.devnabeel.testapplication.domain.model.Task
import com.devnabeel.testapplication.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        return apiService.getTasks().tasks.map { dto ->
            println("API Response: $dto")
            Task(id = dto.id, title = dto.Title, description = dto.Description, duedate = dto.DueDate ,nodayleft = dto.TargetDate)

        }
    }
}