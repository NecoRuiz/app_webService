package com.example.app_webservice.ui.reunion

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.data.api.ApiClient
import com.example.app_webservice.data.repository.ReunionRepository
import java.text.SimpleDateFormat
import java.util.*

@Preview(showBackground = true)
@Composable
fun ReunionScreenPreview() {
    val fakeRepository = ReunionRepository(ApiClient.odooApi)
    val fakeViewModel = ReunionViewModel(fakeRepository)
    val fakeNavController = rememberNavController()

    ReunionScreen(viewModel = fakeViewModel, navController = fakeNavController)
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReunionScreen(viewModel: ReunionViewModel, navController: NavHostController) {

    val context = LocalContext.current
    val datePickerState = rememberDatePickerState()
    var hora by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    val estado = "pendiente"
    val toastMessage = viewModel.toastMessage.value

    LaunchedEffect(toastMessage) {
        if (toastMessage.isNotEmpty()) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
            viewModel.clearToastMessage()
            if (toastMessage.contains("✅")) {
                navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Solicitar reunión", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        DatePicker(state = datePickerState)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = hora,
            onValueChange = { hora = it },
            label = { Text("Hora (HH:mm)") },
            placeholder = { Text("00:00") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = motivo,
            onValueChange = { motivo = it },
            label = { Text("Motivo") },
            placeholder = { Text("Ej: Entrevista, consulta...") },
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
                    hora.isBlank() -> {
                        Toast.makeText(context, "Introduce una hora", Toast.LENGTH_SHORT).show()
                    }
                    motivo.isBlank() -> {
                        Toast.makeText(context, "Introduce un motivo", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        viewModel.solicitarReunion(
                            fecha = selectedDate,
                            hora = hora,
                            motivo = motivo,
                            estado = estado
                        )
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Solicitar", color = Color.White)
        }
    }
}


