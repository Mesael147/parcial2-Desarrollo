package com.example.parcial2.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.parcial2.DAO.ProductosDao
import com.example.parcial2.DAO.ClientesDao
import com.example.parcial2.DAO.VentasDao
import com.example.parcial2.Model.Productos
import com.example.parcial2.Model.Clientes
import com.example.parcial2.Model.Ventas
import com.example.parcial2.Model.DateConverter

@Database(entities = [Productos::class, Clientes::class, Ventas::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productosDao(): ProductosDao
    abstract fun clientesDao(): ClientesDao
    abstract fun ventasDao(): VentasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


