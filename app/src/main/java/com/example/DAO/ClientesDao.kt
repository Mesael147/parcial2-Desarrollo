package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.parcial2.Model.Clientes

@Dao
interface ClientesDao {

    @Insert
    suspend fun insertarCliente(cliente: Clientes): Long

    @Update
    suspend fun actualizarCliente(cliente: Clientes)

    @Delete
    suspend fun eliminarCliente(cliente: Clientes)

    @Query("SELECT * FROM Clientes WHERE clienteId = :id")
    suspend fun obtenerClientePorId(id: Int): Clientes?

    @Query("SELECT * FROM Clientes")
    suspend fun obtenerTodosLosClientes(): List<Clientes>


}


