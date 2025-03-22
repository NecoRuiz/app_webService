package com.example.app_webservice.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.app_webservice.R
import com.example.app_webservice.data.api.ApiClient
import com.example.app_webservice.data.repository.UserRepository
import com.example.app_webservice.ui.theme.fredokaFmily
import kotlinx.coroutines.delay
import kotlin.random.Random



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val fakeRepository = UserRepository(ApiClient.odooApi)
    val fakeViewModel = LoginViewModel(fakeRepository)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.white) // Color de fondo general
    ) {
        LoginScreen(viewModel = fakeViewModel)
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    //gracias al email, engancho las vistas con el liveData del viewModel
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    //variable para controlar el estado del formlario, por defecto--->false, cuando se ckicle--->true
    var showRegisterDialog by remember { mutableStateOf(false) }
    //variables para capturar los datos de los usuarios
    var registerName by remember { mutableStateOf("") }
    var registerEmail by remember { mutableStateOf("") }
    var registerPassword by remember { mutableStateOf("") }
    //val context = LocalContext.current
    val toastMessage by viewModel.toastMessage
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(toastMessage) {
        if (!toastMessage.isNullOrEmpty()) {
            snackbarHostState.showSnackbar(
                message = toastMessage!!,
                duration = SnackbarDuration.Short
            )
                delay(500)
            viewModel.toastMessage.value = null // Reseteamos el mensaje
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo de arriba
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = R.color.teal_700)),
            contentAlignment = Alignment.Center
        ) {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.Center)
            ){ snackbarData ->
                Snackbar(
                    snackbarData,
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    containerColor = Color(0xFF4CAF50), 
                    contentColor = Color.White
                )
            }
            //llamada a la funcion de las burbujas
            BouncingBubbles()

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

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
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    ) // Bordes superiores redondeados
                ),
            contentAlignment = Alignment.TopCenter // Asegura que el contenido empiece desde arriba
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp), // Espaciado entre elementos
                modifier = Modifier.offset(y = (10.dp))
            ) {
                Text(
                    text = "Login",
                    fontFamily = fredokaFmily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 35.sp,
                    color = colorResource(id = R.color.teal_700),
                    modifier = Modifier.padding(
                        bottom = 8.dp,
                        end = 190.dp
                    )
                )
                //cada tecla que el usser pulse, onLoginChange llama al viewModel para comprobar si es valido
                //se pasa el ultimo estado de cada campo
                EmailField(email, { viewModel.onLoginChanged(it, password) })
                PassWordField(password) { viewModel.onLoginChanged(email, it) }
                //cada vez que pulsemos el boton, llama al metodo del viewModel
                ButtonLogin(loginEnable) {}

                Text(

                    text = "¿No tienes cuenta?, REGÍSTRATE",
                    color = colorResource(R.color.blue),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { showRegisterDialog = true }
                    )
            }
        }
    }
    //se abre el formulario
    if (showRegisterDialog) {
        //si el user pincha fuera de la pantalla, se sale
        Dialog(onDismissRequest = {showRegisterDialog = false }) {
            //fondo blanco
            Surface(

                shape = RoundedCornerShape(20.dp),
                color = colorResource( id = R.color.teal_700),
                modifier = Modifier.padding(16.dp)

            ){
                //organizacion de contenido 
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),


                ) { Text(
                    //titulo de la ventana
                    text = "Registro de ususario",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,

                )

                    Spacer(modifier = Modifier.height(8.dp))
                    //campo de texto a rellenar con texto flotante
                    OutlinedTextField(
                        value = registerName,
                        onValueChange = { registerName = it },
                        label = { Text("Nombre completo", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        ),
                        modifier = Modifier.fillMaxWidth()

                    )
                    OutlinedTextField(
                        value = registerEmail,
                        onValueChange = { registerEmail = it },
                        label = { Text("Email", color = Color.Gray) }, // Color del label
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        ),
                        modifier = Modifier.fillMaxWidth()

                    )
                    OutlinedTextField(
                        value = registerPassword,
                        onValueChange = { registerPassword = it },
                        label = { Text("Contraseña", color = Color.Gray) },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        ),
                        modifier = Modifier.fillMaxWidth()

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    //creo una linea que ocupa el ancho y alineo los botones
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Button(
                            onClick = { showRegisterDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,  // Fondo blanco
                                contentColor = Color.Black  // Texto negro
                            )
                        ){
                            Text("Cancelar")
                        }
                        Button(
                            onClick = {
                                showRegisterDialog = false
                                println("Botón de registro presionado")
                                viewModel.registerUser(registerName, registerEmail, registerPassword)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,  // Fondo blanco
                                contentColor = Color.Black  // Texto negro
                            )
                        )  {
                            Text("Crear Cuenta")
                        }

                    }


                }

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
            disabledContainerColor = colorResource(id = R.color.gainsboro),  // Color cuando está deshabilitado
            contentColor = colorResource(id = R.color.black), // Color del texto cuando está habilitado
            disabledContentColor = colorResource(id = R.color.white) // Color del texto cuando está deshabilitado
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
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {

    TextField(
        //cada vez que se pulse una tecla-->
        // llama a OnvalueChange-->
        // coge el valor it(lo que escribe el usser)-->
        // se lo pasa a la var email-->
        // email es el valor del textfield-->
        // textfield se actualiza
        value = email, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth(0.8f) // Ajusta el ancho del campo de texto
            .padding(16.dp),
        placeholder = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,//true=no se amplia el campo a medida que se escribe
        maxLines = 1,
        shape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomStart = 15.dp,
            bottomEnd = 15.dp
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black)
        )
    )
}

//password
@Composable
fun PassWordField(password: String, onTextFieldChanged: (String) -> Unit) {

    TextField(
        //msimo proceso que en el email
        value = password, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp),
        placeholder = { Text("PassWord") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomStart = 15.dp,
            bottomEnd = 15.dp
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black)
        ),
        visualTransformation = PasswordVisualTransformation()
    )


}

// Extensión para generar números aleatorios en rangos Float
fun ClosedFloatingPointRange<Float>.random(): Float =
    Random.nextFloat() * (endInclusive - start) + start

@Composable
fun BouncingBubbles() {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    // Convertimos el tamaño de la pantalla a píxeles
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    // Lista de colores para las burbujas
    val bubbleColors = listOf(
        Color(0xFFa8d5ba), // Verde pastel
        Color(0xFFb2c9f7), // Azul pastel
        Color(0xFFcce0e1), // Azul claro
        Color(0xFFb0e0a8)  // Verde menta claro
    )

    // Creamos una lista de burbujas
    val bubbles = remember {
        List(10) {
            Bubble(
                radius = (20..50).random().toFloat(),
                color = bubbleColors.random(),
                position = mutableStateOf(
                    Offset(
                        x = (0f..screenWidthPx).random(),
                        y = (0f..screenHeightPx * 0.5f).random() // La mitad superior de la pantalla
                    )
                ),
                velocity = Offset(
                    x = ((1..5).random() * if (Random.nextBoolean()) 1 else -1).toFloat(),
                    y = ((1..5).random() * if (Random.nextBoolean()) 1 else -1).toFloat()
                )
            )
        }
    }

    // Dibujamos las burbujas
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            bubbles.forEach { bubble ->
                drawCircle(
                    color = bubble.color,
                    radius = bubble.radius,
                    center = bubble.position.value
                )
            }
        }
    }

    // Animamos cada burbuja de forma individual
    bubbles.forEach { bubble ->
        LaunchedEffect(bubble) {
            while (true) {
                moveBubble(bubble, screenWidthPx, screenHeightPx)
                delay(16L) // ~60 FPS
            }
        }
    }
}

// Función auxiliar que mueve la burbuja y maneja el rebote
suspend fun moveBubble(bubble: Bubble, screenWidthPx: Float, screenHeightPx: Float) {
    val currentPos = bubble.position.value
    var newVelocity = bubble.velocity

    // Calculamos la nueva posición
    val newX = currentPos.x + newVelocity.x
    val newY = currentPos.y + newVelocity.y

    // Verificamos colisiones en el eje X
    if (newX - bubble.radius < 0 || newX + bubble.radius > screenWidthPx) {
        newVelocity = newVelocity.copy(x = -newVelocity.x)
    }
    // Verificamos colisiones en el eje Y (limitado a la mitad superior)
    if (newY - bubble.radius < 0 || newY + bubble.radius > screenHeightPx * 0.5f) {
        newVelocity = newVelocity.copy(y = -newVelocity.y)
    }

    // Actualizamos la velocidad y posición de la burbuja
    bubble.velocity = newVelocity
    bubble.position.value = Offset(
        x = (currentPos.x + newVelocity.x).coerceIn(bubble.radius, screenWidthPx - bubble.radius),
        y = (currentPos.y + newVelocity.y).coerceIn(
            bubble.radius,
            screenHeightPx * 0.5f - bubble.radius
        )
    )
}

// Clase que representa una burbuja
data class Bubble(
    val radius: Float,
    val color: Color,
    val position: MutableState<Offset>,
    var velocity: Offset
)








