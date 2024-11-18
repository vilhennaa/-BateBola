package com.cao.batebola.dados.dao.Time

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cao.batebola.dados.entity.Time.TimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTime(entity: TimeEntity)

    @Delete
    suspend fun deleteTime(entity: TimeEntity)

    @Query("SELECT * FROM times WHERE id = :id")
    suspend fun getTimeById(id: Long): TimeEntity?

    @Query("SELECT * FROM times")
    fun getAllTimes(): Flow<List<TimeEntity>>
}