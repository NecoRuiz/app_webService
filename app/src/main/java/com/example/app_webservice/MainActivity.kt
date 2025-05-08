package com.example.app_webservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.app_webservice.data.api.ApiClient
import com.example.app_webservice.data.repository.ReservaRepository
import com.example.app_webservice.data.repository.ReunionRepository
import com.example.app_webservice.data.repository.UserRepository
import com.example.app_webservice.ui.core.AppNavigation
import com.example.app_webservice.ui.login.LoginViewModel
import com.example.app_webservice.ui.reserva.ReservaViewModel
import com.example.app_webservice.ui.reunion.ReunionViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val repository = UserRepository(ApiClient.odooApi)
            val loginViewModel = LoginViewModel(repository)

            val reservaRepository = ReservaRepository(ApiClient.odooApi)
            val reservaViewModel = ReservaViewModel(reservaRepository)

            val reunionRepository = ReunionRepository(ApiClient.odooApi)
            val reunionViewModel = ReunionViewModel(reunionRepository)

            AppNavigation(
                loginViewModel = loginViewModel,
                reservaViewModel = reservaViewModel,
                reunionViewModel = reunionViewModel
            )
        }
    }
}




