package com.example.app_webservice.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.ui.init.InitScreen
import com.example.app_webservice.ui.login.LoginScreen
import com.example.app_webservice.ui.login.LoginViewModel

@Composable
fun AppNavigation(viewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            InitScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(viewModel)
        }
    }
}