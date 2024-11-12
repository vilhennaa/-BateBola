package com.cao.batebola.dados.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jogadores")
data class JogadorEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val posicao: String,
    val idade: Int,
    val capitao: Boolean = false
)