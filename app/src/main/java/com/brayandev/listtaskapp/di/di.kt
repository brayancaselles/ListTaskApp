package com.brayandev.listtaskapp.di

import com.brayandev.listtaskapp.data.TaskRepository
import com.brayandev.listtaskapp.domain.AddTaskUseCase
import com.brayandev.listtaskapp.domain.DeleteTaskUseCase
import com.brayandev.listtaskapp.domain.GetAllTaskUseCase
import com.brayandev.listtaskapp.presentation.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::TaskRepository)
}

val viewModelModule = module {
    viewModelOf(::TaskViewModel)
}

val domainModule = module {
    single { GetAllTaskUseCase(get()) }
    single { AddTaskUseCase(get()) }
    single { DeleteTaskUseCase(get()) }
}
