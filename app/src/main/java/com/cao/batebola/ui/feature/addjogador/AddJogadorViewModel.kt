package com.cao.batebola.ui.feature.addjogador

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cao.batebola.dados.repository.Jogador.JogadorRepository
import com.cao.batebola.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddJogadorViewModel(
    private val jogadorId: Long? = null,
    private val repository: JogadorRepository
//    private val timeId: Long,
) : ViewModel() {

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
        jogadorId?.let {
            viewModelScope.launch {
                val jogador = repository.getJogadorById(it)
                if (jogador != null) {
                    nome = jogador.nome
                    posicao = jogador.posicao
                    idade = jogador.idade
                    capitao = jogador.capitao
                } else {
                    _uiEvent.send(UiEvent.ShowSnackbar("Jogador não encontrado"))
                }
            }
        }
    }

    fun onEvent(event: AddJogadorEvent) {
        when (event) {
            is AddJogadorEvent.OnNomeChange -> nome = event.nome
            is AddJogadorEvent.OnPosicaoChange -> posicao = event.posicao
            is AddJogadorEvent.OnIdadeChange -> idade = event.idade
            is AddJogadorEvent.IsCapitaoChange -> capitao = event.capitao
            is AddJogadorEvent.OnNavigateBack -> viewModelScope.launch { _uiEvent.send(UiEvent.NavigateBack) }
            is AddJogadorEvent.OnSaveJogadorClick -> saveJogador()
        }
    }

    private fun saveJogador() {
        viewModelScope.launch {
            if (nome.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("O nome do jogador não pode estar em branco"))
                return@launch
            }
            if (posicao.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("A posição do jogador não pode estar em branco"))
                return@launch
            }
            if (idade <= 0) {
                _uiEvent.send(UiEvent.ShowSnackbar("A idade do jogador é inválida"))
                return@launch
            }
//            if (timeId <= 0) {
//                _uiEvent.send(UiEvent.ShowSnackbar("O ID do time é inválido"))
//                return@launch
//            }

            repository.insertJogador(
                nome = nome,
                posicao = posicao,
                idade = idade,
                capitao = capitao,
                id = jogadorId ?: 0,
//                timeId = timeId
            )
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }
}
