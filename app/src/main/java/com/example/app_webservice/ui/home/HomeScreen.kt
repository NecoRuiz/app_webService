package com.example.app_webservice.ui.home




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_webservice.R
import com.google.accompanist.pager.HorizontalPagerIndicator
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.ui.reserva.ReservaViewModel



@Composable
fun HomeScreen(navController: NavHostController, reservaViewModel: ReservaViewModel) {
    val selectedOption = remember { mutableStateOf("servicio") }
    val pagerState = rememberPagerState()
    val imageList = listOf(
        R.drawable.carusselcleaningservice,
        R.drawable.jaboncarussel,
        R.drawable.carusselindoor,
        R.drawable.robot
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            HorizontalPager(
                count = imageList.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        // Información de la empresa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text("TotalShine", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Urb. San Lázaro, nº27, Sarón, Cantabria, España", fontSize = 12.sp)
            Text("667326749", fontSize = 12.sp)
            Text("adminTotalShine@gmail.com", fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tabs (centrados y con más tamaño)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                "Servicios",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = if (selectedOption.value == "servicio") FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.clickable { selectedOption.value = "servicio" }
            )
            Text(
                "Reuniones",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = if (selectedOption.value == "reunion") FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.clickable { selectedOption.value = "reunion" }
            )
            Text(
                "Historial",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = if (selectedOption.value == "historial") FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.clickable { selectedOption.value = "historial" }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección dinámica
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            when (selectedOption.value) {
                "servicio" -> ServicioSection(
                    viewModel = reservaViewModel,
                    navController = navController
                )
                "reunion" -> ReunionSection(
                    navController = navController
                )
                "historial" -> HistorialSection()
            }
        }


        // Barra inferior fija
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Perfil"
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    contentDescription = "Historial"
                )
            }
        }
    }
}










