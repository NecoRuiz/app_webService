package com.example.app_webservice.ui.login


import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_webservice.data.repository.UserRepository
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: UserRepository) : ViewModel(){

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }
    //logica para decir si el email y la password son validos-->true o false
    //Patterns.EMAIL_ADDRESS.matcher(email).matches()-->expresion regular definida por google para comprobar emails
    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length > 6


    var toastMessage = mutableStateOf<String?>(null)

    fun registerUser(registerName: String, registerEmail: String, registerPassword: String){
        viewModelScope.launch {
            try {
                val response = repository.registerUser(registerName, registerEmail, registerPassword)
                val jsonObject = response?.asJsonObject ?:throw Exception("Respuesta nula")

                val result = jsonObject.get("result")?.asInt
                val errorMessage = jsonObject.get("error")?.asString ?: "Error desconocido"

                if (result != null && result > 0){
                    toastMessage.value = "Cuenta registrda correctamente"
                }else{
                    toastMessage.value = "Error: $errorMessage"
                }
            }catch (e: Exception){
                toastMessage.value = "Error en el registro"
            }
        }
    }

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess
    fun loginUser(email: String, password: String){
        viewModelScope.launch {
            try {
                val uid = repository.authenticateUser(email, password)
                if(uid != null && uid > 0){
                    _loginSuccess.value = true
                    toastMessage.value = "Login correcto"
                }else{
                    _loginSuccess.value = false
                    toastMessage.value = "Login incorrecto "
                }
            }catch (e: Exception){
                _loginSuccess.value = false
                toastMessage.value = "Login incorrecto"
            }
        }
    }
}