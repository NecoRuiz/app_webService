package com.example.app_webservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_webservice.ui.home.ui.HomeScreen
import com.example.app_webservice.ui.login.ui.LoginScreen
import com.example.app_webservice.ui.login.ui.loginViewModel
import com.example.app_webservice.ui.theme.App_WebServiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

               LoginScreen(viewModel = loginViewModel())

            }
        }
    }


