package com.example.app_webservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.app_webservice.data.api.ApiClient
import com.example.app_webservice.data.repository.UserRepository
import com.example.app_webservice.ui.core.AppNavigation
import com.example.app_webservice.ui.login.LoginViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val repository = UserRepository(ApiClient.odooApi)
            val loginViewModel = LoginViewModel(repository)

            AppNavigation(viewModel = loginViewModel)

            }
        }
    }


