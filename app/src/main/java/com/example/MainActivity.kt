package com.example.aplicacionlibros



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.krud.Screen.EditarClienteScreen
import com.example.krud.Screen.EditarProductoScreen
import com.example.parcial2.Database.AppDatabase
import com.example.parcial2.Model.Clientes
import com.example.parcial2.Model.Productos
import com.example.parcial2.Repository.ClientesRepository
import com.example.parcial2.Repository.ProductosRepository
import com.example.parcial2.Repository.VentasRepository
import com.example.parcial2.Screen.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val clientesRepository = ClientesRepository(database.clientesDao())
        val productosRepository = ProductosRepository(database.productosDao())
        val ventasRepository = VentasRepository(database.ventasDao())

        setContent {
            MaterialTheme {
                NavigationComponent(
                    clientesRepository = clientesRepository,
                    productosRepository = productosRepository,
                    ventasRepository = ventasRepository
                )
            }
        }
    }
}

@Composable
fun NavigationComponent(
    clientesRepository: ClientesRepository,
    productosRepository: ProductosRepository,
    ventasRepository: VentasRepository
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Pantalla de inicio
        composable("home") {
            HomeScreen(navController = navController)
        }

        // Pantallas de Clientes
        composable("clientes") {
            ClientesScreen(navController = navController, clientesRepository = clientesRepository)
        }
        composable("crear_cliente") {
            CrearClienteScreen(navController = navController, clientesRepository = clientesRepository)
        }
        composable(route = "editar_cliente/{clienteId}") { backStackEntry ->
            val clienteId = backStackEntry.arguments?.getString("clienteId")?.toIntOrNull()
            var cliente by remember { mutableStateOf<Clientes?>(null) }

            if (clienteId != null) {
                LaunchedEffect(clienteId) {
                    cliente = clientesRepository.obtenerClientePorId(clienteId)
                }
            }

            // Verifica si el cliente no es nulo antes de pasar a la pantalla de edición
            cliente?.let {
                EditarClienteScreen(navController = navController, cliente = it, clientesRepository = clientesRepository)
            }
        }


        // Pantallas de Productos
        composable("productos") {
            ProductosScreen(navController = navController, productosRepository = productosRepository)
        }
        composable("crear_producto") {
            CrearProductoScreen(navController = navController, productosRepository = productosRepository)
        }
        composable("editar_producto/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toIntOrNull()
            var producto by remember { mutableStateOf<Productos?>(null) }

            if (productoId != null) {
                LaunchedEffect(productoId) {
                    producto = productosRepository.obtenerProductoPorId(productoId)
                }
            }

            // Verifica si el producto no es nulo antes de pasar a la pantalla de edición
            producto?.let {
                EditarProductoScreen(
                    navController = navController,
                    productosRepository = productosRepository,
                    producto = it
                )
            }
        }


        // Pantallas de Ventas
        composable("ventas") {
            VentasScreen(navController = navController, ventasRepository = ventasRepository)
        }
        composable("crear_venta") {
            CrearVentasScreen(
                navController = navController,
                ventasRepository = ventasRepository,
                clientesRepository = clientesRepository,
                productosRepository = productosRepository
            )
        }
    }
}








