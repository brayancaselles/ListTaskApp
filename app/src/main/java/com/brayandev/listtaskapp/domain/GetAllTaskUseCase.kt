package com.brayandev.listtaskapp.domain

import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

class GetAllTaskUseCase (private val repository: TaskRepository) {

    operator fun invoke(): Flow<List<TaskModel>> = repository.tasks
}