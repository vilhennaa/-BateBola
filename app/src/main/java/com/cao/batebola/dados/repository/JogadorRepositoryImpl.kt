package com.cao.batebola.dados.repository

import com.cao.batebola.dados.dao.JogadorDao
import com.cao.batebola.dados.entity.JogadorEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JogadorRepositoryImpl(
    private val dao: JogadorDao
): JogadorRepository {

    override suspend fun insertJogador(
        nome: String,
        posicao: String,
        idade: Int,
        capitao: Boolean,
        id: Long
    ) {
        val jogador = id.let {
            dao.getJogadorById(it)?.copy(
                nome = nome,
                posicao = posicao,
                idade = idade,
                capitao = capitao
            ) ?: JogadorEntity(
                nome = nome,
                posicao = posicao,
                idade = idade,
                capitao = capitao
            )
        }

        dao.insertJogador(jogador)
    }

    override suspend fun deleteJogador(id: Long) {
        val jogadorExistente = dao.getJogadorById(id) ?: return
        dao.deleteJogador(jogadorExistente)
    }

    override fun getAllJogadores(): Flow<List<JogadorEntity>> {
        return dao.getAllJogadores().map { jogadores ->
            jogadores.map { jogador ->
                JogadorEntity(
                    id = jogador.id,
                    nome = jogador.nome,
                    posicao = jogador.posicao,
                    idade = jogador.idade,
                    capitao = jogador.capitao
                )
            }
        }
    }

    override suspend fun getJogadorById(id: Long): JogadorEntity? {
        return dao.getJogadorById(id)?.let { jogador ->
            JogadorEntity(
                id = jogador.id,
                nome = jogador.nome,
                posicao = jogador.posicao,
                idade = jogador.idade,
                capitao = jogador.capitao
            )
        }
    }
}

