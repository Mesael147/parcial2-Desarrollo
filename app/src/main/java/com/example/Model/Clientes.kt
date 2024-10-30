package com.example.parcial2.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class Clientes(
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int = 0,
    val nombre: String,
    val correo: String
)

