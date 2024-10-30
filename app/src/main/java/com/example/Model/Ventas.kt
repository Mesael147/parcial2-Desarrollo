package com.example.parcial2.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Ventas",
    foreignKeys = [
        ForeignKey(
            entity = Productos::class,
            parentColumns = ["productoId"],
            childColumns = ["productoId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Clientes::class,
            parentColumns = ["clienteId"],
            childColumns = ["clienteId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Ventas(
    @PrimaryKey(autoGenerate = true)
    val ventaId: Int = 0,
    val clienteId: Int,
    val productoId: Int,
    val cantidad: Int,
    val fecha: Date
)

