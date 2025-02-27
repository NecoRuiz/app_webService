package com.example.app_webservice.ui.login.ui

import androidx.compose.runtime.getValue
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
        LoginScreen(viewModel = loginViewModel())
    }
}

@Composable
fun LoginScreen(viewModel: loginViewModel) {
    //gracias al email, engancho las vistas con el liveData del viewModel
    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnable : Boolean by viewModel.loginEnable.observeAsState(initial = false)



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

                BouncingBubbles()

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
                    modifier = Modifier.offset(y = (10.dp)) // Mueve
                ) {
                    Text(
                        text = "Login",
                        fontFamily = fredokaFmily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 35.sp,
                        color = colorResource(id = R.color.teal_700),
                        modifier = Modifier.padding(bottom = 8.dp, end = 190.dp) // Espacio entre el texto y los campos
                    )
                    //cada tecla que el usser pulse, onLoginChange llama al viewModel para comprobar si es valido
                    //se pasa el ultimo estado de cada campo
                    EmailField(email, {viewModel.onLoginChanged(it, password)})
                    PassWordField(password){viewModel.onLoginChanged(email, it)}
                    //cada vez que pulsemos el boton, llama al metodo del viewModel
                    ButtonLogin(loginEnable) {}
                }
            }







        }

    }



//boton login
@Composable
fun ButtonLogin(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
        .width(200.dp)
        .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_700),  // Color cuando el botón está habilitado
            disabledContainerColor = colorResource(id = R.color.dark_gray),  // Color cuando está deshabilitado
            contentColor = colorResource(id = R.color.light_steel_blue), // Color del texto cuando está habilitado
            disabledContentColor = colorResource(id = R.color.dark_gray) // Color del texto cuando está deshabilitado
        ),
            enabled = loginEnable


    ) {

        Text(
            text = "Login",
            fontFamily = fredokaFmily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )


    }
}

//email
@Composable
fun EmailField(email : String, onTextFieldChanged : (String) -> Unit) {

    TextField(
        //cada vez que se pulse una tecla-->
        // llama a OnvalueChange-->
        // coge el valor it(lo que escribe el usser)-->
        // se lo pasa a la var email-->
        // email es el valor del textfield-->
        // textfield se actualiza
        value = email, onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth(0.8f) // Ajusta el ancho del campo de texto
            .padding(16.dp),
        placeholder = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,//true=no se amplia el campo a medida que se escribe
        maxLines = 1,
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black)
        )
    )
}
//password
@Composable
fun PassWordField(password : String, onTextFieldChanged : (String) -> Unit){

    TextField(

        value = password, onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth(0.8f) // Ajusta el ancho del campo de texto
            .padding(16.dp),
        placeholder = { Text("PassWord") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black)
        ),
        visualTransformation = PasswordVisualTransformation()
    )


}

@Composable
fun BouncingBubbles() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Colores pastel predefinidos
    val bubbleColors = listOf(
        Color(0xFFa8d5ba), // Verde pastel
        Color(0xFFb2c9f7), // Azul pastel
        Color(0xFFcce0e1), // Azul claro
        Color(0xFFb0e0a8)  // Verde menta claro
    )

    val bubbles = remember {
        List(10) { // Número de burbujas
            Bubble(
                radius = (20..50).random(),
                color = bubbleColors.random(),
                position = Offset(
                    x = (0..screenWidth.value.toInt()).random().toFloat(),
                    y = (0..screenHeight.value.toInt()).random().toFloat()
                ),
                velocity = Offset(
                    x = ((1..5).random() * if (Math.random() > 0.5) 1 else -1).toFloat(),
                    y = ((1..5).random() * if (Math.random() > 0.5) 1 else -1).toFloat()
                )
            )
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        bubbles.forEach { bubble ->
            drawCircle(
                color = bubble.color,
                radius = bubble.radius.toFloat(),
                center = bubble.position
            )
        }

        // Actualizamos la posición de las burbujas y las rebote
        bubbles.forEach { bubble ->
            val newX = bubble.position.x + bubble.velocity.x
            val newY = bubble.position.y + bubble.velocity.y

            // Rebote en el eje X
            if (newX - bubble.radius < 0 || newX + bubble.radius > screenWidth.value) {
                bubble.velocity = bubble.velocity.copy(x = -bubble.velocity.x)
            }

            // Rebote en el eje Y
            if (newY - bubble.radius < 0 || newY + bubble.radius > screenHeight.value) {
                bubble.velocity = bubble.velocity.copy(y = -bubble.velocity.y)
            }

            // Actualiza la posición de la burbuja
            bubble.position = Offset(
                x = newX.coerceIn(bubble.radius.toFloat(), screenWidth.value - bubble.radius),
                y = newY.coerceIn(bubble.radius.toFloat(), screenHeight.value - bubble.radius)
            )
        }
    }
}

// Clase para representar una burbuja
data class Bubble(
    var radius: Int,
    var color: Color,
    var position: Offset,
    var velocity: Offset
)






