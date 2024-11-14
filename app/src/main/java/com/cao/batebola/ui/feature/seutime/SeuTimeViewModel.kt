package com.cao.batebola.ui.feature.seutime

import AddJogadorRoute
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cao.batebola.dados.repository.JogadorRepository
import com.cao.batebola.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SeuTimeViewModel (
    private val repository: JogadorRepository
): ViewModel() {
    val jogadores = repository.getAllJogadores()
        .stateIn(
            scope = viewModelScope,
            started =  SharingStarted.WhileSubscribed(5000),
            initialValue =  emptyList()
        )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SeuTimeEvent) {
        when(event) {
            is SeuTimeEvent.DeleteJogador -> {
                deleteJogador(event.id)
            }
            is SeuTimeEvent.AddJogador -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(AddJogadorRoute(event.id)))
                }
            }
        }
    }

    private fun deleteJogador(id: Long) {
        viewModelScope.launch {
            repository.deleteJogador(id)
        }
    }
}
