package com.brayandev.listtaskapp.domain

import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.domain.model.TaskModel

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.updateTask(taskModel)
    }
}