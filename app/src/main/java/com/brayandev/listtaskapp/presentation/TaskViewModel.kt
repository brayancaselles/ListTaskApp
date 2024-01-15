package com.brayandev.listtaskapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brayandev.listtaskapp.domain.AddTaskUseCase
import com.brayandev.listtaskapp.domain.DeleteTaskUseCase
import com.brayandev.listtaskapp.domain.GetAllTaskUseCase
import com.brayandev.listtaskapp.domain.UpdateTaskUseCase
import com.brayandev.listtaskapp.domain.model.TaskModel
import com.brayandev.listtaskapp.presentation.UiState.Success
import com.brayandev.listtaskapp.presentation.UiState.Error
import com.brayandev.listtaskapp.presentation.UiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    getAllTaskUseCase: GetAllTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    val uiState: StateFlow<UiState> = getAllTaskUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onDialogOpen() {
        _showDialog.value = true
    }

    fun onTaskCreated(nameStore: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(nameTask = nameStore))
        }
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        viewModelScope.launch { updateTaskUseCase(taskModel.copy(isSelected = !taskModel.isSelected)) }
    }

    fun onTaskRemove(taskModel: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }
    }
}
