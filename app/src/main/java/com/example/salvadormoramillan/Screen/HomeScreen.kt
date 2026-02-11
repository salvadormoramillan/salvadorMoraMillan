package com.example.salvadormoramillan.Screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.salvadormoramillan.ViewModel.HomeViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    OnLogout : () -> Unit
) {
    val state by viewModel.homeState.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var posicion by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    LaunchedEffect(state.selectedPlayer) {
        state.selectedPlayer?.let { jugador ->
            nombre = jugador.nombre
            posicion = jugador.posicion
            nacionalidad = jugador.nacionalidad
            imagen = jugador.imagen
            number = jugador.number.toString()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = posicion,
                onValueChange = { posicion = it },
                label = { Text("Posición") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = nacionalidad,
                onValueChange = { nacionalidad = it },
                label = { Text("Nacionalidad") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = number,
                onValueChange = { number = it },
                label = { Text("Número") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = imagen,
                onValueChange = { imagen = it },
                label = { Text("URL de la imagen") },
                placeholder = { Text("https://ejemplo.com/imagen.jpg") },
                modifier = Modifier.fillMaxWidth()

            )
        }

        item {
            Button(
                onClick = {
                    val num = number.toIntOrNull() ?: 0
                    if (state.selectedPlayer != null) {
                        viewModel.editarJugador(
                            id = state.selectedPlayer!!.id,
                            nombre = nombre,
                            posicion = posicion,
                            nacionalidad = nacionalidad,
                            imagen = imagen,
                            number = num
                        )
                    } else {
                        viewModel.agregarjugador(
                            nombre = nombre,
                            posicion = posicion,
                            nacionalidad = nacionalidad,
                            imagen = imagen,
                            number = num
                        )
                    }

                    nombre = ""
                    posicion = ""
                    nacionalidad = ""
                    imagen = ""
                    number = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (state.selectedPlayer != null) "Guardar cambios"
                    else "Añadir jugador"
                )
            }
        }
        item {
            Button(
                onClick = {OnLogout()}){
                Text("Salir")

            }
        }

        state.error?.let {
            item {
                Text(
                    text = it

                )
            }
        }

        state.success?.let {
            item {
                Text(
                    text = it
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}