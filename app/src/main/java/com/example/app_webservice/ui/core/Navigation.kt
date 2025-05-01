package com.example.app_webservice.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.ui.home.HomeScreen
import com.example.app_webservice.ui.init.InitScreen
import com.example.app_webservice.ui.login.LoginScreen
import com.example.app_webservice.ui.login.LoginViewModel
import com.example.app_webservice.ui.reserva.ReservaScreen
import com.example.app_webservice.ui.reserva.ReservaViewModel

@Composable
fun AppNavigation(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    val reservaViewModel: ReservaViewModel = viewModel()
    val loginSuccess by loginViewModel.loginSuccess.observeAsState(false)

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            InitScreen(navController)
        }

        composable(Screen.Login.route) {
            LaunchedEffect(loginSuccess) {
                if (loginSuccess) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            }
            LoginScreen(viewModel = loginViewModel)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController, reservaViewModel = reservaViewModel)
        }

        composable(Screen.Reserva.route) {
            ReservaScreen(viewModel = reservaViewModel)
        }
    }
}



