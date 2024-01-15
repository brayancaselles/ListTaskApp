package com.brayandev.listtaskapp.data.dataSource

import com.brayandev.listtaskapp.data.dataBase.dao.TaskDao
import com.brayandev.listtaskapp.data.dataBase.entity.toEntity
import com.brayandev.listtaskapp.domain.model.TaskModel
import com.brayandev.listtaskapp.domain.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskDataSource @Inject constructor(private val dao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = dao.getAllTask().map { items -> items.map { it.toModel() } }

    suspend fun insertTask(task: TaskModel) {
        dao.insertTask(task.toEntity())
    }

    suspend fun updateTask(task: TaskModel) {
        dao.updateTask(task.toEntity())
    }

    suspend fun deleteTask(task: TaskModel) {
        dao.deleteTask(task.toEntity())
    }
}