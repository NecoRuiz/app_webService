package com.example.app_webservice.ui.reserva

import android.R
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true)
@Composable
fun ReservaScreenPreview() {
    val fakeViewModel = ReservaViewModel() // o una versión fake/mock
    val fakeNavController = rememberNavController()

    ReservaScreen(viewModel = fakeViewModel, navController = fakeNavController)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservaScreen(viewModel: ReservaViewModel, navController: NavController) {
    val servicio = viewModel.servicioSeleccionado.value ?: ""
    var fechaSeleccionada by remember { mutableStateOf(LocalDate.now()) }
    var horasSeleccionadas by remember { mutableStateOf(4) }
    var ubicacion by remember { mutableStateOf("") }
    val precio = horasSeleccionadas * 12
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_media_previous),
                    contentDescription = "Volver"
                )
            }
            Text("Reserva de servicio", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Servicio: $servicio", fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Fecha seleccionada:", fontWeight = FontWeight.Medium)
        Text(fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))

        // Aquí se puede añadir un calendario más adelante

        Spacer(modifier = Modifier.height(16.dp))

        Text("Horas de servicio:", fontWeight = FontWeight.Medium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            (4..8).forEach { hora ->
                Box(
                    modifier = Modifier
                        .background(if (horasSeleccionadas == hora) Color(0xFF00796B) else Color.LightGray)
                        .clickable { horasSeleccionadas = hora }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text("$hora h", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Ubicación:", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            placeholder = { Text("Introduce la dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Precio: $precio €", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Reserva creada para el día ${fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}.",
                    Toast.LENGTH_LONG
                ).show()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Confirmar reserva")
        }
    }
}

