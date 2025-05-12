package com.example.app_webservice.ui.core

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Reservas : Screen("reserva")
    object Reuniones : Screen("reunion")
    object HistorialServicios : Screen("historial_servicios")
    object HistorialReuniones : Screen("historial_reuniones")
}