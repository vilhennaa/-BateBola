package com.cao.batebola.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cao.batebola.dados.dao.Jogador.JogadorDao
import com.cao.batebola.dados.dao.Time.TimeDao
import com.cao.batebola.dados.entity.Jogador.JogadorEntity
import com.cao.batebola.dados.entity.Time.TimeEntity

@Database(entities = [JogadorEntity::class, TimeEntity::class], version = 3)
abstract class BateBolaDatabase : RoomDatabase() {
    abstract val jogadorDao: JogadorDao
    abstract val timeDao: TimeDao
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
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}
