package com.example.parcial2.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0)) // Fondo cálido
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a la App de Gestión",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF795548),  // Marrón cálido
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para ver clientes
        Button(
            onClick = { navController.navigate("clientes") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFAB91)), // Salmón claro
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Ver Clientes", color = Color(0xFF4E342E))  // Texto marrón oscuro
        }

        // Botón para ver productos
        Button(
            onClick = { navController.navigate("productos") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC80)), // Naranja claro
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Ver Productos", color = Color(0xFF5D4037))  // Texto marrón medio
        }

        // Botón para ver ventas
        Button(
            onClick = { navController.navigate("ventas") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE082)), // Amarillo cálido
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Ver Ventas", color = Color(0xFF6D4C41))  // Texto marrón oscuro
        }
    }
}





