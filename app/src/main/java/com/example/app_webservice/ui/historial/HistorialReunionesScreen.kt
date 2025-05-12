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
import com.example.app_webservice.ui.reunion.ReunionViewModel
import com.example.app_webservice.data.model.Reunion

@Composable
fun HistorialReunionesScreen(
    navController: NavHostController,
    viewModel: ReunionViewModel
) {
    val context = LocalContext.current
    val reuniones by viewModel.historialReuniones

    // Cargar reuniones al entrar en la pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarHistorialReuniones()
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
            text = "Historial de Reuniones",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (reuniones.isEmpty()) {
            Text("No hay reuniones registradas.", color = Color.Gray)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(reuniones) { reunion: Reunion ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Fecha: ${reunion.fecha}")
                            Text("Hora: ${reunion.hora}")
                            Text("Motivo: ${reunion.motivo}")
                            Text("Estado: ${reunion.estado}")
                        }
                    }
                }
            }
        }
    }
}
