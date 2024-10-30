package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.krud.Model.VentasConClienteYProducto
import com.example.parcial2.Model.Ventas

@Dao
interface VentasDao {

    @Insert
    suspend fun insertarVenta(venta: Ventas): Long

    @Query("SELECT * FROM Ventas WHERE ventaId = :id")
    suspend fun obtenerVentaPorId(id: Int): Ventas?

    @Query(
        """
        SELECT Ventas.*, Clientes.nombre AS clienteNombre, Productos.nombre AS productoNombre 
        FROM Ventas 
        INNER JOIN Clientes ON Ventas.clienteId = Clientes.clienteId 
        INNER JOIN Productos ON Ventas.productoId = Productos.productoId 
        WHERE Ventas.clienteId = :clienteId
    """
    )
    suspend fun obtenerVentasPorCliente(clienteId: Int): List<Ventas>

    @Query(
        """
        SELECT Ventas.*, Clientes.nombre AS clienteNombre, Productos.nombre AS productoNombre 
        FROM Ventas 
        INNER JOIN Clientes ON Ventas.clienteId = Clientes.clienteId 
        INNER JOIN Productos ON Ventas.productoId = Productos.productoId 
        WHERE Ventas.productoId = :productoId
    """
    )
    suspend fun obtenerVentasPorProducto(productoId: Int): List<Ventas>

    /*@Query("SELECT * FROM Ventas")
    suspend fun obtenerTodasLasVentas(): List<Ventas>*/
    @Transaction
    @Query("SELECT * FROM Ventas")
    suspend fun obtenerVentasConClienteYProducto(): List<VentasConClienteYProducto>


}


