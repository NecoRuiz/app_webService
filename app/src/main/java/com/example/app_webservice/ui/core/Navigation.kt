package com.example.app_webservice.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.ui.historial.HistorialReunionesScreen
import com.example.app_webservice.ui.historial.HistorialServiciosScreen
import com.example.app_webservice.ui.home.HomeScreen
import com.example.app_webservice.ui.init.InitScreen
import com.example.app_webservice.ui.login.LoginScreen
import com.example.app_webservice.ui.login.LoginViewModel
import com.example.app_webservice.ui.reserva.ReservaScreen
import com.example.app_webservice.ui.reserva.ReservaViewModel
import com.example.app_webservice.ui.reunion.ReunionScreen
import com.example.app_webservice.ui.reunion.ReunionViewModel

@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel,
    reservaViewModel: ReservaViewModel,
    reunionViewModel: ReunionViewModel
) {
    val navController = rememberNavController()
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
            HomeScreen(
                navController = navController,
                reservaViewModel = reservaViewModel
            )
        }

        composable(Screen.Reservas.route) {
            ReservaScreen(
                viewModel = reservaViewModel,
                navController = navController
            )
        }

        composable(Screen.Reuniones.route) {
            ReunionScreen(
                viewModel = reunionViewModel,
                navController = navController
            )
        }

        composable(Screen.HistorialServicios.route) {
            HistorialServiciosScreen(
                navController = navController,
                viewModel = reservaViewModel
            )
        }

        composable(Screen.HistorialReuniones.route) {
            HistorialReunionesScreen(
                navController = navController,
                viewModel = reunionViewModel
            )
        }
    }

}




