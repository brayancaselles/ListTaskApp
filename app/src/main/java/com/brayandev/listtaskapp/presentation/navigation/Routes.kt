package com.brayandev.listtaskapp.presentation.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object TaskList : Routes("task_list")
}
