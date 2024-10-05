package com.devnabeel.testapplication.domain.usecase

import com.devnabeel.testapplication.domain.model.Task
import com.devnabeel.testapplication.domain.repository.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend fun execute(): List<Task> {
        return taskRepository.getTasks() ?: emptyList()
    }
}