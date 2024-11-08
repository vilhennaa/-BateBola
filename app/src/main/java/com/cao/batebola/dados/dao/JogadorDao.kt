package com.cao.batebola.dados.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cao.batebola.dados.model.Jogador
import kotlinx.coroutines.flow.Flow

@Dao
interface JogadorDao {

    // Listar todos os jogadores
    @Query("SELECT * FROM jogador")
    fun listarJogadores(): Flow<List<Jogador>>

    // Buscar um jogador por ID
    @Query("SELECT * FROM jogador WHERE id = :jogadorId")
    suspend fun buscarJogadorPorId(jogadorId: Int): Jogador

    // Inserir ou atualizar jogador
    @Upsert
    suspend fun gravarJogador(jogador: Jogador)

    // Excluir jogador
    @Delete
    suspend fun excluirJogador(jogador: Jogador)
}