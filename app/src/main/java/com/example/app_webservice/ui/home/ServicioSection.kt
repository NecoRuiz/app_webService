package com.example.app_webservice.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.navigation.NavHostController
import com.example.app_webservice.ui.core.Screen
import com.example.app_webservice.ui.reserva.ReservaViewModel
import com.example.app_webservice.ui.theme.fredokaFmily


@Composable
fun ServicioSection(
    viewModel: ReservaViewModel,
    navController: NavHostController
) {
    val servicios = listOf(
        "Limpieza de vivienda",
        "Limpieza de oficina",
        "Limpieza de hotel",
        "Limpieza de comunidad"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Top)
    ) {
        servicios.forEach { servicio ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    servicio,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "12€/h",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Button(
                        onClick = {
                            viewModel.setServicioSeleccionado(servicio)
                            navController.navigate(Screen.Reservas.route)
                                  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00796B),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier
                            .height(36.dp)
                            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                    ) {
                        Text(
                            text = "Reservar",
                            fontFamily = fredokaFmily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}



