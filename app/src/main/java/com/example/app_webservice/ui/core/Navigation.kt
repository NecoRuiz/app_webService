package com.example.app_webservice.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.ui.home.HomeScreen
import com.example.app_webservice.ui.init.InitScreen
import com.example.app_webservice.ui.login.LoginScreen
import com.example.app_webservice.ui.login.LoginViewModel

@Composable
fun AppNavigation(viewModel: LoginViewModel) {
    val navController = rememberNavController()
    val loginSuccess by viewModel.loginSuccess.observeAsState(false)

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            InitScreen(navController)
        }
        composable(Screen.Login.route) {
//new
            LaunchedEffect(loginSuccess) {
                if (loginSuccess) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            }
            LoginScreen(viewModel)
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}
