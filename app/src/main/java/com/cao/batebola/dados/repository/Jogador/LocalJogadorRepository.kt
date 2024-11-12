package com.cao.batebola.dados.repository.Jogador



import JogadorRepository
import com.cao.batebola.dados.dao.JogadorDao
import com.cao.batebola.dados.model.Jogador
import kotlinx.coroutines.flow.Flow

class LocalJogadorRepository(
    private val dao: JogadorDao
): JogadorRepository {

    override fun listarJogadores(): Flow<List<Jogador>> {
        return dao.listarJogadores()
    }

    override suspend fun buscarJogadorPorId(id: Int): Jogador {
        return dao.buscarJogadorPorId(id)
    }

    override suspend fun gravarJogador(jogador: Jogador) {
        return dao.gravarJogador(jogador)
    }

    override suspend fun excluirJogador(jogador: Jogador) {
        return dao.excluirJogador(jogador)
    }
}