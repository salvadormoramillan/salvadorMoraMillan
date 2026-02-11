package com.example.salvadormoramillan.ViewModel

import androidx.lifecycle.ViewModel
import com.example.salvadormoramillan.State.LoginState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private  val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState : StateFlow<LoginState> = _loginState

    fun Login(email: String, password: String){
        if(email.isBlank() || password.isBlank()){
            _loginState.value = LoginState.Error("Email y password estan vacias")
            return
        }

        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                _loginState.value = LoginState.success
            }
            .addOnFailureListener {
                _loginState.value = LoginState.Error("Error al iniciar sesion")
            }
    }
}
