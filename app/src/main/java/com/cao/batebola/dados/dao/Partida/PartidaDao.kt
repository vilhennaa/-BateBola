package com.cao.batebola.dados.dao.Partida

import androidx.room.*
import com.cao.batebola.dados.entity.Partida.Partida
import kotlinx.coroutines.flow.Flow


@Dao
interface PartidaDao {

    @Upsert
    suspend fun inserirPartida(partida: Partida)

    @Query("SELECT * FROM partidas WHERE id = :id LIMIT 1")
    suspend fun getPartidaById(id: Int): Partida?

    @Query("SELECT * FROM partidas")
     fun listarPartidas(): Flow<List<Partida>>

    @Delete
    suspend fun deletarPartida(partida: Partida)



}
