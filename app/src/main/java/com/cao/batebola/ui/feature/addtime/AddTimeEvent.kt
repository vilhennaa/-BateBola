package com.cao.batebola.ui.feature.addtime

interface AddTimeEvent {
    data class NomeChanged(val nome: String) : AddTimeEvent
    data class CidadeChanged(val cidade: String) : AddTimeEvent
    data class EstadoChanged(val estado: String) : AddTimeEvent
    object SaveTime : AddTimeEvent
}