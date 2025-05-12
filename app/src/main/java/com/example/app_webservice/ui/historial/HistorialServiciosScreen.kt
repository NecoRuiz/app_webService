package com.example.app_webservice.ui.historial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.app_webservice.data.model.Reserva
import com.example.app_webservice.ui.reserva.ReservaViewModel

@Composable
fun HistorialServiciosScreen(
    navController: NavHostController,
    viewModel: ReservaViewModel
) {
    val context = LocalContext.current
    val reservas by viewModel.historialReservas

    // Cargar historial una vez al entrar en la pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarHistorial()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black
            )
        }

        Text(
            text = "Historial de Servicios",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (reservas.isEmpty()) {
            Text("No hay servicios registrados.", color = Color.Gray)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(reservas) { reserva: Reserva ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Servicio: ${reserva.tipoServicio}")
                            Text("Fecha: ${reserva.fecha}")
                            Text("Horas: ${reserva.horas}")
                            Text("Ubicación: ${reserva.ubicacion}")
                            Text("Precio: ${reserva.precio}€")
                        }
                    }
                }
            }
        }
    }
}


