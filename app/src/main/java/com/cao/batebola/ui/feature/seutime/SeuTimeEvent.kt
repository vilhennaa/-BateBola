package com.cao.batebola.ui.feature.seutime

sealed interface SeuTimeEvent {
    data class DeleteJogador(val id: Long) : SeuTimeEvent
    data class AddJogador(val id: Long?) : SeuTimeEvent
}