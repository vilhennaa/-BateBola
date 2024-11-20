package com.cao.batebola.dados.repository.Partidas

import com.cao.batebola.dados.dao.Partida.PartidaDao
import com.cao.batebola.dados.entity.Partida.Partida
import kotlinx.coroutines.flow.Flow

class LocalPartidaRepository(
    private val Dao:PartidaDao
):PartidaRepository{
    override suspend fun inserirPartida(partida: Partida) {
        Dao.inserirPartida(partida)
    }

    override fun listarPartidas(): Flow<List<Partida>> {
        return Dao.listarPartidas()

    }

    override suspend fun deletarPartida(partida: Partida) {
        Dao.deletarPartida(partida)
    }

    override suspend fun getPartidaById(id: String): Partida? {
       return Dao.getPartidaById(id)
    }

}