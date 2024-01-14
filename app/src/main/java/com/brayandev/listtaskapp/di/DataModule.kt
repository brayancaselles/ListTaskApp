package com.brayandev.listtaskapp.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.data.dataBase.TaskDataBase
import com.brayandev.listtaskapp.data.dataSource.TaskDataSource
import com.brayandev.listtaskapp.domain.AddTaskUseCase
import com.brayandev.listtaskapp.domain.DeleteTaskUseCase
import com.brayandev.listtaskapp.domain.GetAllTaskUseCase
import com.brayandev.listtaskapp.domain.UpdateTaskUseCase
import com.brayandev.listtaskapp.presentation.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), TaskDataBase::class.java, Constants.TASK_DATABASE)
            .build()
    }
    single { get<TaskDataBase>().taskDao() }
}

val dataModule = module {
    //includes(dataBaseModule)
    factoryOf(::TaskDataSource)
    factoryOf(::TaskRepository)

}

val domainModule = module {
    //includes(dataModule)
    factoryOf(::GetAllTaskUseCase)
    factoryOf(::AddTaskUseCase)
    factoryOf(::DeleteTaskUseCase)
    factoryOf(::UpdateTaskUseCase)
}

val dataAppModule = module {
    includes(domainModule, dataModule, dataBaseModule)
    viewModel() { TaskViewModel(get(), get(), get(), get()) }
}


object Constants {
    const val TASK_DATABASE = "task_table"
}