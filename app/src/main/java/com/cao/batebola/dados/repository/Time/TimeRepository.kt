package com.cao.batebola.dados.repository.Time

import com.cao.batebola.domain.Time
import kotlinx.coroutines.flow.Flow

interface TimeRepository {

    suspend fun insertTime(
        nome: String,
        cidade: String,
        estado: String,
        id: Long? = null
    )

    suspend fun deleteTime(id: Long)

    suspend fun getTimeById(id: Long): Time?

    fun getAllTimes(): Flow<List<Time>>
}