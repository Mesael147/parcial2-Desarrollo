package com.example.krud.Model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.parcial2.Model.Clientes
import com.example.parcial2.Model.Productos
import com.example.parcial2.Model.Ventas

data class VentasConClienteYProducto(
    @Embedded val venta: Ventas,
    @Relation(
        parentColumn = "clienteId",
        entityColumn = "clienteId"
    )
    val cliente: Clientes,
    @Relation(
        parentColumn = "productoId",
        entityColumn = "productoId"
    )
    val producto: Productos
)
