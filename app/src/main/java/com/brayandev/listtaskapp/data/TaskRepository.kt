package com.brayandev.listtaskapp.data

import com.brayandev.listtaskapp.data.dataSource.TaskDataSource
import com.brayandev.listtaskapp.domain.model.TaskModel

class TaskRepository(private val dataSource: TaskDataSource) {

    fun getAllTask() = dataSource.tasks

    suspend fun insertTask(task: TaskModel) = dataSource.insertTask(task)

    suspend fun updateTask(task: TaskModel) = dataSource.updateTask(task)

    suspend fun deleteTask(task: TaskModel) = dataSource.deleteTask(task)
}