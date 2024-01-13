package com.brayandev.listtaskapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brayandev.listtaskapp.domain.AddTaskUseCase
import com.brayandev.listtaskapp.domain.DeleteTaskUseCase
import com.brayandev.listtaskapp.domain.model.TaskModel
import kotlinx.coroutines.launch

class TaskViewModel(
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onDialogOpen() {
        _showDialog.value = true
    }

    fun onTaskCreated(nameStore: String) {
        _showDialog.value = false

        viewModelScope.launch {
            // insertStoreUseCase(StoreModel(store = nameStore))
        }
    }

    fun onTaskRemove(taskModel: TaskModel) {
        viewModelScope.launch {
            // deleteStoreUseCase(nameStore)
        }
    }
}
