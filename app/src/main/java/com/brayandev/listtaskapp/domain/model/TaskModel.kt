package com.brayandev.listtaskapp.domain.model

import com.brayandev.listtaskapp.data.dataBase.entity.TaskEntity

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val nameTask: String,
    val isSelected: Boolean = false
)

fun TaskEntity.toModel() = TaskModel(id = id, nameTask = nameTask, isSelected = isSelected)
