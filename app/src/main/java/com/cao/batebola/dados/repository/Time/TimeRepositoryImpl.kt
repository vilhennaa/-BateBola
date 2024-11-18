package com.cao.batebola.dados.repository.Time

import com.cao.batebola.dados.dao.Time.TimeDao
import com.cao.batebola.dados.entity.Time.TimeEntity
import com.cao.batebola.domain.Time
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TimeRepositoryImpl(
    private val dao: TimeDao
): TimeRepository {
    override suspend fun insertTime(
        nome: String,
        cidade: String,
        estado: String,
        id: Long?
    ) {
        val time = id?.let {
            dao.getTimeById(it)?.copy(
                nome = nome,
                cidade = cidade,
                estado = estado
            )
        } ?: TimeEntity(
            nome = nome,
            cidade = cidade,
            estado = estado
        )

        dao.insertTime(time)
    }

    override suspend fun deleteTime(id: Long) {
        val timeExistente = dao.getTimeById(id) ?: return
        dao.deleteTime(timeExistente)
    }

    override suspend fun getTimeById(id: Long): Time? {
        return dao.getTimeById(id)?.let { time ->
            Time(
                id = time.id,
                nome = time.nome,
                cidade = time.cidade,
                estado = time.estado
            )
        }
    }

    override fun getAllTimes(): Flow<List<Time>> {
        return dao.getAllTimes().map { times ->
            times.map { time ->
                Time(
                    id = time.id,
                    nome = time.nome,
                    cidade = time.cidade,
                    estado = time.estado
                )
            }
        }
    }
}