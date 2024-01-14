package com.brayandev.listtaskapp.data.dataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brayandev.listtaskapp.di.Constants
import com.brayandev.listtaskapp.domain.model.TaskModel

@Entity(tableName = Constants.TASK_DATABASE)
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name_task") val nameTask: String,
    @ColumnInfo(name = "is_selected") val isSelected: Boolean
)

fun TaskModel.toEntity() = TaskEntity(id = id, nameTask = nameTask, isSelected = isSelected)
