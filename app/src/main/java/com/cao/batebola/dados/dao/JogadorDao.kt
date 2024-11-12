package com.cao.batebola.dados.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cao.batebola.dados.entity.JogadorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JogadorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJogador(entity: JogadorEntity)

    @Delete
    suspend fun deleteJogador(entity: JogadorEntity)

    @Query("SELECT * FROM jogadores WHERE id = :id")
    suspend fun getJogadorById(id: Long): JogadorEntity?

    @Query("SELECT * FROM jogadores")
    fun getAllJogadores(): Flow<List<JogadorEntity>>

}