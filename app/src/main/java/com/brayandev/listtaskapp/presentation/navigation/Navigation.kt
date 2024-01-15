package com.brayandev.listtaskapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brayandev.listtaskapp.presentation.splash.SplashScreen
import com.brayandev.listtaskapp.presentation.task.Task

@Composable
fun Navigation() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.Splash.route,
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(navigationController)
        }
        composable(Routes.TaskList.route) {
            Task()
        }

    }
}