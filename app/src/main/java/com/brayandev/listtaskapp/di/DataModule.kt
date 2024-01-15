package com.brayandev.listtaskapp.di

import androidx.room.Room
import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.data.dataBase.TaskDataBase
import com.brayandev.listtaskapp.domain.AddTaskUseCase
import com.brayandev.listtaskapp.domain.DeleteTaskUseCase
import com.brayandev.listtaskapp.domain.GetAllTaskUseCase
import com.brayandev.listtaskapp.domain.UpdateTaskUseCase
import com.brayandev.listtaskapp.presentation.task.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(androidContext(), TaskDataBase::class.java, "AllDataBase")
            .build()
    }
    single { get<TaskDataBase>().taskDao() }
    single { TaskRepository(get()) }
}


val repositoryModule = module {
    single { TaskRepository(get()) }
}


val useCaseModule = module {
    factoryOf(::GetAllTaskUseCase)
    factoryOf(::AddTaskUseCase)
    factoryOf(::DeleteTaskUseCase)
    factoryOf(::UpdateTaskUseCase)
}


val dataAppModule = module {

    viewModel {
        TaskViewModel(
            getAllTaskUseCase = get(),
            addTaskUseCase = get(),
            deleteTaskUseCase = get(),
            updateTaskUseCase = get()
        )
    }
}
