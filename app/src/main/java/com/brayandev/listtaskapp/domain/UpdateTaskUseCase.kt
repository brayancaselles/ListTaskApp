package com.brayandev.listtaskapp.domain

import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.domain.model.TaskModel

class UpdateTaskUseCase (private val repository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        repository.updateTask(taskModel)
    }
}