package com.brayandev.listtaskapp.presentation.task

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Task
import androidx.compose.material.icons.rounded.TaskAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.brayandev.listtaskapp.domain.model.TaskModel
import com.brayandev.listtaskapp.presentation.task.UiState.Error
import com.brayandev.listtaskapp.presentation.task.UiState.Loading
import com.brayandev.listtaskapp.presentation.task.UiState.Success
import org.koin.androidx.compose.koinViewModel

@Composable
fun Task(viewModel: TaskViewModel = koinViewModel()) {
    val showDialog: Boolean by viewModel.showDialog.observeAsState(false)

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<UiState>(
        initialValue = Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }

    when (uiState) {
        is Error -> {
            Log.d(
                "Error",
                "ah ocurrido el siguiente error -> ${(uiState as Error).throwable}"
            )
        }

        Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }

        is Success -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Title("Lista de Tareas", Icons.Rounded.TaskAlt)
                Box(modifier = Modifier.fillMaxSize()) {
                    AddTaskDialog(
                        show = showDialog,
                        onDismiss = { viewModel.onDialogClose() },
                        onStoreAdded = { viewModel.onTaskCreated(it) },
                    )
                    FabDialog(modifier = Modifier.align(Alignment.BottomEnd), viewModel)
                    TaskList((uiState as Success).tasks, viewModel)
                }
            }
        }
    }


}

@Composable
fun Title(tittle: String, imageVector: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 16.dp),
            imageVector = imageVector,
            contentDescription = "Icon_title",
        )
        Text(
            text = tittle,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(show: Boolean, onDismiss: () -> Unit, onStoreAdded: (String) -> Unit) {
    var nameStore by rememberSaveable { mutableStateOf("") }

    AnimatedVisibility(visible = show, enter = expandVertically(), exit = shrinkVertically()) {
        Dialog(
            onDismissRequest = { onDismiss() },
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Añade una nueva tarea",
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    OutlinedTextField(
                        value = nameStore,
                        onValueChange = { nameStore = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = "Icon_task_dialog",
                            )
                        },
                        label = { Text(text = "Nombre de la tarea") },
                        placeholder = {
                            Text(text = "Añadir nombre")
                        },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(
                        onClick = {
                            onStoreAdded(nameStore)
                            nameStore = ""
                        },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Añadir tarea")
                    }
                }
            }
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, viewModel: TaskViewModel) {
    FloatingActionButton(
        onClick = { viewModel.onDialogOpen() },
        modifier = modifier.padding(16.dp),
    ) {
        Row {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Icon_Add")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    taskList: List<TaskModel>,
    viewModel: TaskViewModel,
) {

    val taskListGrouped = taskList.groupBy { it.isSelected }

    LazyColumn {

        taskListGrouped.forEach { (isSelected, tasks) ->
            stickyHeader {
                Text(
                    text = if (isSelected) "Completadas" else "Pendientes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }

            items(tasks,key = { it.id }) { item ->
                ItemTask(item, viewModel)
            }
        }
    }
}

@Composable
fun ItemTask(task: TaskModel, taskViewModel: TaskViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { taskViewModel.onTaskRemove(task) },
                    onPress = { },
                )
            },
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 16.dp),
                imageVector = Icons.Rounded.Task,
                contentDescription = "Icon_task",
                tint = Color.Black,
            )
            Text(
                text = task.nameTask,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                color = Color.Black,
            )
            Checkbox(
                checked = task.isSelected,
                onCheckedChange = { taskViewModel.onCheckBoxSelected(task) },
            )

            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        taskViewModel.onTaskRemove(task)
                    },
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Icon_delete_task",
                tint = Color.Red,
            )
        }
    }
}
