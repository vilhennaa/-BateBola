package com.cao.batebola.dados.repository.Partidas

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cao.batebola.dados.entity.Partida.Partida
import com.cao.batebola.domain.Jogador
import kotlinx.coroutines.flow.Flow

interface PartidaRepository {

    suspend fun inserirPartida(partida: Partida)

    fun listarPartidas(): Flow<List<Partida>>

    suspend fun deletarPartida(partida: Partida)

    suspend fun getPartidaById(id: Int): Partida?


}