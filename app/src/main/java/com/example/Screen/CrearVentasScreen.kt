package com.example.parcial2.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.Model.Clientes
import com.example.parcial2.Model.Productos
import com.example.parcial2.Model.Ventas
import com.example.parcial2.Repository.ClientesRepository
import com.example.parcial2.Repository.ProductosRepository
import com.example.parcial2.Repository.VentasRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CrearVentasScreen(
    navController: NavController,
    ventasRepository: VentasRepository,
    clientesRepository: ClientesRepository,
    productosRepository: ProductosRepository
) {
    val coroutineScope = rememberCoroutineScope()

    var selectedCliente by remember { mutableStateOf<Clientes?>(null) }
    var selectedProducto by remember { mutableStateOf<Productos?>(null) }
    var cantidad by remember { mutableStateOf("") }
    var fechaVenta by remember { mutableStateOf("") } // Nuevo campo para la fecha
    var clientesList by remember { mutableStateOf<List<Clientes>>(emptyList()) }
    var productosList by remember { mutableStateOf<List<Productos>>(emptyList()) }

    LaunchedEffect(Unit) {
        clientesList = clientesRepository.obtenerTodosLosClientes()
        productosList = productosRepository.obtenerTodosLosProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {
        Text(
            text = "Registrar Nueva Venta",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF795548),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0B2)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Selección de Cliente
                Text("Seleccionar Cliente", color = Color(0xFF6D4C41), fontWeight = FontWeight.Bold)
                clientesList.forEach { cliente ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(
                                if (selectedCliente == cliente) Color(0xFFFFCCBC) else Color.Transparent
                            )
                            .clickable { selectedCliente = cliente }
                            .padding(8.dp)
                    ) {
                        Text(cliente.nombre)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Selección de Producto
                Text("Seleccionar Producto", color = Color(0xFF6D4C41), fontWeight = FontWeight.Bold)
                productosList.forEach { producto ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(
                                if (selectedProducto == producto) Color(0xFFFFCCBC) else Color.Transparent)
                            .clickable { selectedProducto = producto }
                            .padding(8.dp)
                    ) {
                        Text(producto.nombre)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo para la cantidad
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo para la fecha de venta
                OutlinedTextField(
                    value = fechaVenta,
                    onValueChange = { fechaVenta = it },
                    label = { Text("Fecha de Venta (dd/MM/yyyy)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para guardar la venta
                Button(
                    onClick = {
                        if (selectedCliente != null && selectedProducto != null && cantidad.isNotBlank() && fechaVenta.isNotBlank()) {
                            coroutineScope.launch {
                                val parsedDate = try {
                                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(fechaVenta) ?: Date()
                                } catch (e: Exception) {
                                    Date() // Si la fecha no es válida, usa la fecha actual
                                }

                                val nuevaVenta = Ventas(
                                    clienteId = selectedCliente!!.clienteId,
                                    productoId = selectedProducto!!.productoId,
                                    cantidad = cantidad.toInt(),
                                    fecha = parsedDate
                                )
                                ventasRepository.insertarVenta(nuevaVenta)
                                navController.popBackStack() // Regresar a la lista de ventas después de guardar
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar Venta")
                }
            }
        }
    }
}



