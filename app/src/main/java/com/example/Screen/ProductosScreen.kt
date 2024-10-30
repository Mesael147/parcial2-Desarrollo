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
import com.example.parcial2.Model.Productos
import com.example.parcial2.Repository.ProductosRepository
import kotlinx.coroutines.launch


@Composable
fun ProductosScreen(navController: NavController, productosRepository: ProductosRepository) {
    val coroutineScope = rememberCoroutineScope()
    var productosList by remember { mutableStateOf<List<Productos>>(emptyList()) }

    // Cargar la lista de productos al inicio
    LaunchedEffect(Unit) {
        productosList = productosRepository.obtenerTodosLosProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Productos",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF795548),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(productosList) { producto ->
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
                            text = "Nombre: ${producto.nombre}",
                            color = Color(0xFF6D4C41),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Precio: $${producto.precio}",
                            color = Color(0xFF8D6E63)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Stock: ${producto.stock}",
                            color = Color(0xFF8D6E63)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("editar_producto/${producto.productoId}")
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD180)), // Naranja claro
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Editar", color = Color(0xFF5D4037)) // Texto marrón oscuro
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        productosRepository.eliminarProducto(producto)
                                        productosList = productosRepository.obtenerTodosLosProductos()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)), // Rojo fuerte
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

        // Botones para agregar y regresar en la parte inferior con colores cálidos
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
                Text("Regresar a Listado", color = Color(0xFF5D4037)) // Texto marrón oscuro
            }

            Button(
                onClick = { navController.navigate("crear_producto") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFAB40)), // Amarillo cálido
                modifier = Modifier.weight(1f)
            ) {
                Text("Agregar Nuevo Producto", color = Color(0xFF4E342E)) // Texto marrón oscuro
            }
        }
    }
}

