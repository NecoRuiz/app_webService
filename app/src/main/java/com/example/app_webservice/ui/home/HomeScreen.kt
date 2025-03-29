package com.example.app_webservice.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_webservice.R

@Preview
@Composable
fun HomeScreen() {
    val selectedOption = remember { mutableStateOf("servicio") }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()
            .background(Color.White)) {
            // Imagen superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray)
            ) {
                // Aquí puedes poner un carrusel más adelante
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Info del negocio
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("TotalShine", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Urb. San Lázaro, nº27, Sarón, Cantabria, España", fontSize = 12.sp)
                Text("667326749", fontSize = 12.sp)
                Text("adminTotalShine@gmail.com", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de sección
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("Servicios",
                    fontWeight = if (selectedOption.value == "servicio") FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.clickable { selectedOption.value = "servicio" })

                Text("Reuniones",
                    fontWeight = if (selectedOption.value == "reunion") FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.clickable { selectedOption.value = "reunion" })

                Text("Historial",
                    fontWeight = if (selectedOption.value == "historial") FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.clickable { selectedOption.value = "historial" })
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección dinámica
            when (selectedOption.value) {
                "servicio" -> ServicioSection()
                "reunion" -> ReunionSection()
                "historial" -> HistorialSection()
            }

            Spacer(modifier = Modifier.weight(1f))

            // Barra inferior
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Navegar o lógica futura */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_home_24),
                        contentDescription = "Home"
                    )
                }
                IconButton(onClick = { /* Usuario */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "Perfil"
                    )
                }
                IconButton(onClick = { /* Notificaciones o historial */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_notifications_24),
                        contentDescription = "Historial"
                    )
                }
            }
        }
    }
}





