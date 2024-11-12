package com.cao.batebola.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cao.batebola.dados.dao.JogadorDao
import com.cao.batebola.dados.entity.JogadorEntity

@Database(entities = [JogadorEntity::class], version = 1)
abstract class JogadorDatabase : RoomDatabase() {

    abstract fun jogadorDao(): JogadorDao

    companion object {

        @Volatile
        private var INSTANCE: JogadorDatabase? = null

        fun getInstance(context: Context): JogadorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JogadorDatabase::class.java,
                    "batebola.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}