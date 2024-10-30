package com.example.parcial2.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Productos")
data class Productos(
    @PrimaryKey(autoGenerate = true)
    val productoId: Int = 0,
    val nombre: String,
    val precio: Double,
    val stock: Int
)
