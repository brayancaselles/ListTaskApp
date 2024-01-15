package com.brayandev.listtaskapp.di

/*
val dataBaseModule = module {

    single {
        Room.databaseBuilder(androidContext(), TaskDataBase::class.java, "task_table")
            .build()
    }
    single { get<TaskDataBase>().taskDao() }
}

val dataSourceModule = module {
    singleOf(::TaskDataSource) */
/*{ TaskDataSource(get()) }*//*

}

val repositoryModule = module {
    factoryOf(::TaskRepository)
}

val dataModule = listOf(dataBaseModule, dataSourceModule, repositoryModule)

val useCaseModule = module {
    */
/*factory { GetAllTaskUseCase(repository = get()) }
    factory { AddTaskUseCase(repository = get()) }
    factory { DeleteTaskUseCase(repository = get()) }
    factory { UpdateTaskUseCase(repository = get()) }*//*

    factoryOf(::GetAllTaskUseCase)
    factoryOf(::AddTaskUseCase)
    factoryOf(::DeleteTaskUseCase)
    factoryOf(::UpdateTaskUseCase)
}

val domainModule = listOf(useCaseModule)

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


object Constants {
    const val TASK_DATABASE = "task_table"
}*/
