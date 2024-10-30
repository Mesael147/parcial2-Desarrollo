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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.krud.Model.VentasConClienteYProducto
import com.example.parcial2.Model.Ventas
import com.example.parcial2.Repository.VentasRepository
import kotlinx.coroutines.launch

@Composable
fun VentasScreen(navController: NavController, ventasRepository: VentasRepository) {
    val coroutineScope = rememberCoroutineScope()
    var ventasList by remember { mutableStateOf<List<VentasConClienteYProducto>>(emptyList()) }

    LaunchedEffect(Unit) {
        ventasList = ventasRepository.obtenerVentasConClienteYProducto()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Ventas",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF795548),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)  // Hace que la lista ocupe el espacio disponible
        ) {
            items(ventasList) { ventaConClienteYProducto ->
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
                            text = "Cliente: ${ventaConClienteYProducto.cliente.nombre}",
                            color = Color(0xFF6D4C41),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Producto: ${ventaConClienteYProducto.producto.nombre}",
                            color = Color(0xFF8D6E63)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Cantidad: ${ventaConClienteYProducto.venta.cantidad}",
                            color = Color(0xFF8D6E63)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Fecha: ${ventaConClienteYProducto.venta.fecha}",
                            color = Color(0xFF8D6E63)
                        )
                    }
                }
            }
        }

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
                onClick = { navController.navigate("crear_venta") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFAB40)), // Amarillo cálido
                modifier = Modifier.weight(1f)
            ) {
                Text("Agregar Nueva Venta", color = Color(0xFF4E342E))
            }
        }
    }
}




