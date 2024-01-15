package com.brayandev.listtaskapp.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.brayandev.listtaskapp.presentation.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigationController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(3000)
        navigationController.popBackStack()
        navigationController.navigate(Routes.TaskList.route)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Rounded.AddTask,
            contentDescription = "splash_logo",
            modifier = Modifier.size(150.dp, 150.dp),
        )
        Text(text = "Bienvenidos", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        CircularProgressIndicator()
    }
}