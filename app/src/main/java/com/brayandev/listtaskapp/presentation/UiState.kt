package com.brayandev.listtaskapp.presentation

import com.brayandev.listtaskapp.domain.model.TaskModel

sealed interface UiState {
    object Loading : UiState
    data class Error(val throwable: Throwable) : UiState
    data class Success(val tasks: List<TaskModel>) : UiState
}