package com.example.app_webservice.ui.reserva


import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.data.api.ApiClient
import com.example.app_webservice.data.repository.ReservaRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Preview(showBackground = true)
@Composable
fun ReservaScreenPreview() {
    val fakeRepository = ReservaRepository(ApiClient.odooApi) // Usa el mismo que ya tienes
    val fakeViewModel = ReservaViewModel(fakeRepository)
    val fakeNavController = rememberNavController()

    ReservaScreen(viewModel = fakeViewModel, navController = fakeNavController)
}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservaScreen(viewModel: ReservaViewModel, navController: NavHostController) {

    val context = LocalContext.current

    val servicio = viewModel.servicioSeleccionado.value ?: "No seleccionado"
    val datePickerState = rememberDatePickerState()
    var horasSeleccionadas by remember { mutableStateOf(4) }
    var ubicacion by remember { mutableStateOf("") }
    val toastMessage = viewModel.toastMessage.value
    val precio = horasSeleccionadas * 12

    // Mostrar Toast si hay mensaje desde el ViewModel
    LaunchedEffect(toastMessage) {
        if (toastMessage.isNotEmpty()) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
            viewModel.toastMessage.value = "" // Limpiar para no repetir
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black
            )
        }

        Text("Tipo de servicio: $servicio", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        DatePicker(state = datePickerState)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Horas de servicio:", fontWeight = FontWeight.Medium, fontSize = 16.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            (4..8).forEach { hora ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (horasSeleccionadas == hora) Color(0xFF00796B) else Color.LightGray)
                        .clickable { horasSeleccionadas = hora }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("$hora h", color = Color.White, fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicación") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(millis))
                }

                when {
                    selectedDate.isNullOrBlank() -> {
                        Toast.makeText(context, "Selecciona una fecha", Toast.LENGTH_SHORT).show()
                    }
                    ubicacion.isBlank() -> {
                        Toast.makeText(context, "Introduce una ubicación", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        viewModel.confirmarReserva(
                            tipoServicio = servicio,
                            fecha = selectedDate,
                            horas = horasSeleccionadas,
                            ubicacion = ubicacion,
                            precio = precio
                        )

                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Confirmar reserva ($precio€)", color = Color.White)
        }
    }
}




