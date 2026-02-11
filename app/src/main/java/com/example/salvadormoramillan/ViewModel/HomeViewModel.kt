package com.example.salvadormoramillan.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salvadormoramillan.Data.BaseDeDatos
import com.example.salvadormoramillan.State.HomeState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {

    private  val  firestore = FirebaseFirestore.getInstance()
    private val collectio = firestore.collection("Jugadores")


    private val _homeState = MutableStateFlow(HomeState())
    val homeState : StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        obtenerJugadores()
    }

    private fun obtenerJugadores(){
        viewModelScope.launch {
            _homeState.value = _homeState.value.copy(isLoading = true)

            try {

                val snapshot = collectio.get().await()

                val jugadores = snapshot.mapNotNull { jugadores ->
                    BaseDeDatos(
                        id = jugadores.id,
                        nombre = jugadores.getString("nombre") ?: "",
                        posicion = jugadores.getString("posicion") ?: "",
                        nacionalidad = jugadores.getString("nacionalidad") ?: "",
                        imagen = jugadores.getString("imagen") ?: "",
                        number = jugadores.getLong("number")?.toInt() ?: 0

                        )
                }

                _homeState.value = _homeState.value.copy(jugadores = jugadores, isLoading = false)


            }catch (e: Exception){
                _homeState.value = _homeState.value.copy(error = e.message)
            }
        }
    }

    fun agregarjugador(nombre: String, posicion: String, nacionalidad: String, imagen: String, number: Int){
        viewModelScope.launch {
            _homeState.value = _homeState.value.copy(isLoading = true, error = null)

            try{

                val jugador = hashMapOf(
                    "nombre" to nombre,
                    "posicion" to posicion,
                    "nacionalidad" to nacionalidad,
                    "imagen" to imagen,
                    "number" to number
                )
                collectio.add(jugador).await()
                _homeState.value = _homeState.value.copy(success = "Jugador agregado con exito")

                obtenerJugadores()

                kotlinx.coroutines.delay(2000)
                Limpiarmensage()

            }catch (e : Exception){
                _homeState.value = _homeState.value.copy(error = e.message)
            }
        }
    }

    private fun Limpiarmensage(){
        _homeState.value = _homeState.value.copy(success = null,error = null)

    }

    fun Eliminarjugador(id: String){
        viewModelScope.launch {
            try {
                collectio.document(id).delete().await()
                obtenerJugadores()
            }catch (e : Exception){
                _homeState.value = _homeState.value.copy(error = e.message)
            }
        }
    }
}