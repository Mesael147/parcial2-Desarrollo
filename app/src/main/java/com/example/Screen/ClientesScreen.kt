package com.example.parcial2.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.Model.Clientes
import com.example.parcial2.Repository.ClientesRepository
import kotlinx.coroutines.launch

@Composable
fun ClientesScreen(navController: NavController, clientesRepository: ClientesRepository) {
    val coroutineScope = rememberCoroutineScope()
    var clientesList by remember { mutableStateOf<List<Clientes>>(emptyList()) }


    LaunchedEffect(Unit) {
        clientesList = clientesRepository.obtenerTodosLosClientes()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Clientes",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF795548),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(clientesList) { cliente ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCCBC)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .shadow(4.dp, RoundedCornerShape(12.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Nombre: ${cliente.nombre}",
                            color = Color(0xFF6D4C41),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Correo: ${cliente.correo}",
                            color = Color(0xFF8D6E63)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("editar_cliente/${cliente.clienteId}")
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB74D)), // Naranja cálido
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Editar", color = Color(0xFF4E342E))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        clientesRepository.eliminarCliente(cliente)
                                        clientesList = clientesRepository.obtenerTodosLosClientes()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)), // Rojo fuerte para eliminar
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Eliminar", color = Color.White)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD180)), // Naranja claro
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Regresar a Listado", color = Color(0xFF5D4037))
            }

            Button(
                onClick = { navController.navigate("crear_cliente") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFAB40)), // Amarillo cálido
                modifier = Modifier.weight(1f)
            ) {
                Text("Agregar Nuevo Cliente", color = Color(0xFF4E342E))
            }
        }
    }
}





