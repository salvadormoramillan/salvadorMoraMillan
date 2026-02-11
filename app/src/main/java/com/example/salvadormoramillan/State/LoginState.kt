package com.example.salvadormoramillan.State

sealed class LoginState(){
    data object Idle : LoginState()
    data object success : LoginState()
    data class Error(val message: String) : LoginState()
}