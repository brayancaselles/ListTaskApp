package com.brayandev.listtaskapp.domain.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val nameTask: String,
)
