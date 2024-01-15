package com.brayandev.listtaskapp.domain

import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.domain.model.TaskModel

class DeleteTaskUseCase (private val repository: TaskRepository) {

    suspend operator fun invoke(task: TaskModel) = repository.deleteTask(task)
}
