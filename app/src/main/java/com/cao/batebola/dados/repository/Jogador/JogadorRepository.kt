package com.cao.batebola.dados.repository.Jogador

import com.cao.batebola.domain.Jogador
import kotlinx.coroutines.flow.Flow

interface JogadorRepository {

    suspend fun insertJogador(
        nome: String,
        posicao: String,
        idade: Int,
        capitao: Boolean,
        id: Long,
//        timeId: Long
    )

    suspend fun deleteJogador(id: Long)

    fun getAllJogadores(): Flow<List<Jogador>>

    suspend fun getJogadorById(id: Long): Jogador?
}