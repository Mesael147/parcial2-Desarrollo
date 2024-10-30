package com.example.parcial2.Repository

import com.example.krud.Model.VentasConClienteYProducto
import com.example.parcial2.DAO.VentasDao
import com.example.parcial2.Model.Ventas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VentasRepository(private val ventaDao: VentasDao) {


    /*  suspend fun obtenerTodasLasVentas(): List<Ventas> = withContext(Dispatchers.IO) {
        ventaDao.obtenerTodasLasVentas()
    }*/
    suspend fun obtenerVentasConClienteYProducto(): List<VentasConClienteYProducto> {
        return ventaDao.obtenerVentasConClienteYProducto()
    }


    suspend fun insertarVenta(venta: Ventas) = withContext(Dispatchers.IO) {
        ventaDao.insertarVenta(venta)
    }


}
