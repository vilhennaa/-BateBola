package com.cao.batebola.dados.repository.Jogador

import com.cao.batebola.dados.dao.Jogador.JogadorDao
import com.cao.batebola.dados.entity.Jogador.JogadorEntity
import com.cao.batebola.domain.Jogador
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
        id: Long,
//        timeId: Long
    ) {
        val jogador = id.let {
            dao.getJogadorById(it)?.copy(
                nome = nome,
                posicao = posicao,
                idade = idade,
                capitao = capitao,
//                timeId = timeId
            ) ?: JogadorEntity(
                nome = nome,
                posicao = posicao,
                idade = idade,
                capitao = capitao,
//                timeId = timeId
            )
        }

        dao.insertJogador(jogador)
    }

    override suspend fun deleteJogador(id: Long) {
        val jogadorExistente = dao.getJogadorById(id) ?: return
        dao.deleteJogador(jogadorExistente)
    }

    override fun getAllJogadores(): Flow<List<Jogador>> {
        return dao.getAllJogadores().map { jogadores ->
            jogadores.map { jogador ->
                Jogador(
                    id = jogador.id,
                    nome = jogador.nome,
                    posicao = jogador.posicao,
                    idade = jogador.idade,
                    capitao = jogador.capitao,
//                    timeId = jogador.timeId
                )
            }
        }
    }

    override suspend fun getJogadorById(id: Long): Jogador? {
        return dao.getJogadorById(id)?.let { jogador ->
            Jogador(
                id = jogador.id,
                nome = jogador.nome,
                posicao = jogador.posicao,
                idade = jogador.idade,
                capitao = jogador.capitao,
//                timeId = jogador.timeId
            )
        }
    }
}


