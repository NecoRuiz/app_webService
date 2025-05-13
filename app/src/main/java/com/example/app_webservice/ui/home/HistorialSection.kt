package com.example.app_webservice.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_webservice.ui.core.Screen

@Composable
fun HistorialSection(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Historial", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Button(
                onClick = { navController.navigate(Screen.HistorialServicios.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Ver servicios", color = Color.White)
            }

            Button(
                onClick = { navController.navigate(Screen.HistorialReuniones.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Ver reuniones", color = Color.White)
            }
        }
    }
}

