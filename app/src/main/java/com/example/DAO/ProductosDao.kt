package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.parcial2.Model.Productos

@Dao
interface ProductosDao {

    @Insert
    suspend fun insertarProducto(producto: Productos): Long

    @Update
    suspend fun actualizarProducto(producto: Productos)

    @Delete
    suspend fun eliminarProducto(producto: Productos)

    @Query("SELECT * FROM Productos WHERE productoId = :id")
    suspend fun obtenerProductoPorId(id: Int): Productos?

    @Query("SELECT * FROM Productos")
    suspend fun obtenerTodosLosProductos(): List<Productos>

    @Query("UPDATE Productos SET stock = stock - :cantidad WHERE productoId = :productoId")
    suspend fun actualizarStock(productoId: Int, cantidad: Int)
}

