package com.example.app_webservice.ui.reserva

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ReservaScreen(viewModel: ReservaViewModel) {
    val servicio = viewModel.servicioSeleccionado.value

    Column() {
        Text("Servicio: $servicio")
        // Aquí irán los campos de fecha, hora, ubicación, etc.
    }
}
