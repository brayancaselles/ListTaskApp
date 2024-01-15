package com.brayandev.listtaskapp.domain

import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTaskUseCase @Inject constructor(private val repository: TaskRepository) {

    operator fun invoke(): Flow<List<TaskModel>> = repository.tasks
}