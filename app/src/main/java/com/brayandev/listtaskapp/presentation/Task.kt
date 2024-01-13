package com.brayandev.listtaskapp.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Store
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.brayandev.listtaskapp.domain.model.TaskModel

@Composable
fun Task(viewModel: TaskViewModel) {
    val showDialog: Boolean by viewModel.showDialog.observeAsState(false)

    Column(modifier = Modifier.fillMaxWidth()) {
        Title("Lista de Tareas", Icons.Filled.List)
        Box(modifier = Modifier.fillMaxSize()) {
            AddTaskDialog(
                show = showDialog,
                onDismiss = { viewModel.onDialogClose() },
                onStoreAdded = { viewModel.onTaskCreated(it) },
            )
            FabDialog(modifier = Modifier.align(Alignment.BottomEnd), viewModel)
            // storeList((uiState as Success).list, storeViewModel, navController)
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

@Composable
fun TaskList(
    taskList: List<TaskModel>,
    viewModel: TaskViewModel,
) {
    LazyColumn {
        items(taskList, key = { it.id }) { item ->
            ItemTask(item, viewModel)
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
            modifier = Modifier.fillMaxWidth().height(40.dp),
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 16.dp),
                imageVector = Icons.Rounded.Store,
                contentDescription = "Icon_task",
                tint = Color.Black,
            )
            Text(
                text = task.nameTask,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                color = Color.Black,
            )
        }
    }
}
