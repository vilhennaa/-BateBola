package com.cao.batebola.dados.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entidade ou tabela do banco de dados para jogadores
@Entity
data class Jogador(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nome: String,
    val posicao: String,
    val idade: Int,
    val capitao: Boolean = false
)