package com.cao.batebola.dados.entity.Time

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "times")
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val cidade: String,
    val estado: String
)