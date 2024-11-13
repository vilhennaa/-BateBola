package com.cao.batebola.ui.feature.addjogador

interface AddJogadorEvent {
    data class OnNomeChange(val nome: String) : AddJogadorEvent
    data class OnPosicaoChange(val posicao: String) : AddJogadorEvent
    data class OnIdadeChange(val idade: Int) : AddJogadorEvent
    data class IsCapitaoChange(val capitao: Boolean) : AddJogadorEvent
    object OnSaveJogadorClick : AddJogadorEvent
}