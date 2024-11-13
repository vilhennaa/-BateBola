package com.cao.batebola.ui.feature.addjogador

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cao.batebola.dados.repository.JogadorRepository
import com.cao.batebola.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddJogadorViewModel(
    private val id: Long? = null,
    private val repository: JogadorRepository,
): ViewModel() {

    var nome by mutableStateOf("")
        private set

    var posicao by mutableStateOf("")
        private set

    var idade by mutableStateOf(0)
        private set

    var capitao by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        id?.let {
            viewModelScope.launch {
                val jogador = repository.getJogadorById(it)
                nome = jogador?.nome ?: ""
                posicao = jogador?.posicao ?: ""
                idade = jogador?.idade ?: 0
                capitao = jogador?.capitao ?: false
            }
        }
    }

    fun onEvent(event: AddJogadorEvent) {
        when(event) {
            is AddJogadorEvent.OnNomeChange -> { nome = event.nome }
            is AddJogadorEvent.OnPosicaoChange -> { posicao = event.posicao }
            is AddJogadorEvent.OnIdadeChange -> { idade = event.idade }
            is AddJogadorEvent.IsCapitaoChange -> { capitao = event.capitao }
            is AddJogadorEvent.OnSaveJogadorClick -> { saveJogador() }
        }
    }

    private fun saveJogador() {
        viewModelScope.launch {
            if (nome.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar(message = "O nome do jogador não pode estar em branco"))
                return@launch
            }
            if (posicao.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar(message = "A posição do jogador não pode estar em branco"))
                return@launch
            }
            if (idade <= 0) {
                _uiEvent.send(UiEvent.ShowSnackbar(message = "A idade do jogador é inválida"))
                return@launch
            }

            repository.insertJogador(
                nome = nome,
                posicao = posicao,
                idade = idade,
                capitao = capitao,
                id = id ?: 0
            )
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }
}
