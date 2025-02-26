package com.example.app_webservice.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_webservice.R
import com.example.app_webservice.ui.theme.fredokaFmily

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.white) // Color de fondo general
    ) {
        LoginScreen()
    }
}

@Composable
fun LoginScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo principal (toda la pantalla)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource( id = R.color.teal_700)),
            contentAlignment = Alignment.Center
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally){

                Text(
                    text = "¡Hola!",
                    fontFamily = fredokaFmily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 50.sp,
                    color = colorResource(id = R.color.light_steel_blue),
                    modifier = Modifier.padding(top = 100.dp, end = 250.dp, start = 10.dp)
                )
                Text(

                    text = "Bienvenido a TotalShine",
                    fontFamily = fredokaFmily,
                    fontWeight = FontWeight.Thin,
                    fontSize = 25.sp,
                    color = colorResource(id = R.color.light_steel_blue),
                    modifier = Modifier.padding(top = 70.dp, bottom = 50.dp, end = 20.dp)

                )

            }

        }



        // Box inferior superpuesto (mitad de pantalla)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f) // Ocupa la parte inferior
                .align(Alignment.BottomCenter) // Se coloca en la parte inferior
                .background(
                    color = colorResource(id = R.color.light_steel_blue),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp) // Bordes superiores redondeados
                ),
            contentAlignment = Alignment.TopCenter // Asegura que el contenido empiece desde arriba
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp), // Espaciado entre elementos
                modifier = Modifier.offset(y = (10.dp)) // Mueve todo el bloque hacia arriba
            ) {
                Text(
                    text = "Login",
                    fontFamily = fredokaFmily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 35.sp,
                    color = colorResource(id = R.color.teal_700),
                    modifier = Modifier.padding(bottom = 8.dp, end = 190.dp) // Espacio entre el texto y los campos
                )

                EmailField()
                PassWordField()
                ButtonLogin()
            }
        }







    }
}

@Composable
fun ButtonLogin() {
    Button(onClick = { }, modifier = Modifier
        .width(200.dp)
        .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_700),  // Color cuando el botón está habilitado
            disabledContainerColor = colorResource(id = R.color.dark_gray),  // Color cuando está deshabilitado
            contentColor = colorResource(id = R.color.light_steel_blue), // Color del texto cuando está habilitado
            disabledContentColor = colorResource(id = R.color.dark_gray) // Color del texto cuando está deshabilitado
        )


    ) {

        Text(
            text = "Login",
            fontFamily = fredokaFmily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )


    }
}


@Composable
fun EmailField() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth(0.8f) // Ajusta el ancho del campo de texto
            .padding(16.dp),
        placeholder = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,//true=no se amplia el campo a medida que se escribe
        maxLines = 1,
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.light_steel_blue),
            unfocusedTextColor = colorResource(id = R.color.white)
        )
    )
}

@Composable
fun PassWordField(){

    TextField(
        
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth(0.8f) // Ajusta el ancho del campo de texto
            .padding(16.dp),
        placeholder = { Text("PassWord") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.light_steel_blue),
            unfocusedTextColor = colorResource(id = R.color.white)
        )
    )


}


