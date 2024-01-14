package com.brayandev.listtaskapp.domain

import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.domain.model.TaskModel

class AddTaskUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(task: TaskModel) = repository.insertTask(task)
}
