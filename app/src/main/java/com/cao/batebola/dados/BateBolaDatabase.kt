package com.cao.batebola.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cao.batebola.dados.dao.JogadorDao
import com.cao.batebola.dados.entity.JogadorEntity

@Database(entities = [JogadorEntity::class], version = 1)
abstract class BateBolaDatabase : RoomDatabase() {

    abstract val jogadorDao: JogadorDao
}

object BateBolaDatabaseProvider {

    @Volatile
    private var INSTANCE: BateBolaDatabase? = null

    fun provide(context: Context): BateBolaDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BateBolaDatabase::class.java,
                "batebola.db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
