package com.cao.batebola.dados.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cao.batebola.dados.model.Jogador
import kotlinx.coroutines.flow.Flow

@Dao
interface JogadorDao {

    @Query("SELECT * FROM jogador")
    fun listarJogadores(): Flow<List<Jogador>>

    @Query("SELECT * FROM jogador WHERE id = :jogadorId")
    suspend fun buscarJogadorPorId(jogadorId: Int): Jogador

    @Upsert
    suspend fun gravarJogador(jogador: Jogador)

    @Delete
    suspend fun excluirJogador(jogador: Jogador)
}