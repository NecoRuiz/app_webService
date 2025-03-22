package com.example.app_webservice.ui.init


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_webservice.R
import com.example.app_webservice.ui.core.Screen
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@Composable
private fun InitScreenPreviewOnly() {
    InitScreen(rememberNavController())
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewHome() {
    InitScreenPreviewOnly()
}
@Composable
fun InitScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.companylogo2),
            contentDescription = "Logo TotalShine",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}









