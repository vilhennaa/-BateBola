package com.cao.batebola.dados.entity.Partida

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partidas")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val titulo: String,
    val timeA: String,
    val timeB: String,
    val data: String,
    val local: String
){
    constructor() : this(null, "", "", "","","")
}