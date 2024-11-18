package com.cao.batebola.domain

data class Jogador(
    val id: Long,
    val nome: String,
    val posicao: String,
    val idade: Int,
    val capitao: Boolean,
//    val timeId: Long?
)