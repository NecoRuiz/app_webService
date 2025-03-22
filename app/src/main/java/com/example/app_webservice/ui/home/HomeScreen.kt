package com.example.app_webservice.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeOptionsScreen(onLogoutClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Icono de perfil/logout arriba a la derecha
        IconButton(
            onClick = onLogoutClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil / Logout",
                tint = Color.Black
            )
        }

        // Contenido principal: botones centrados
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "¿Qué deseas hacer?",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            HomeOptionButton(text = "Contratar Servicio", onClick = { /* Navegar a contratar */ })
            Spacer(modifier = Modifier.height(24.dp))

            HomeOptionButton(text = "Solicitar Reunión", onClick = { /* Navegar a reunión */ })
            Spacer(modifier = Modifier.height(24.dp))

            HomeOptionButton(text = "Ver Historial de Servicios", onClick = { /* Navegar a historial */ })
        }
    }
}

@Composable
fun HomeOptionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00897B), // Color teal personalizado
            contentColor = Color.White
        )
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeOptionsScreenPreview() {
    HomeOptionsScreen(onLogoutClick = {})
}

