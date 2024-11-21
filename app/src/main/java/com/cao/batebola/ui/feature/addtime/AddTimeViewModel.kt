package com.cao.batebola.ui.feature.addtime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cao.batebola.dados.repository.Time.TimeRepository
import com.cao.batebola.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddTimeViewModel(
    private val id: Long? = null,
    private val timeRepository: TimeRepository
): ViewModel() {

    var nome by mutableStateOf("")
        private set

    var cidade by mutableStateOf("")
        private set

    var estado by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        id?.let {
            viewModelScope.launch {
                val time = timeRepository.getTimeById(it)
                nome = time?.nome ?: ""
                cidade = time?.cidade ?: ""
                estado = time?.estado ?: ""
            }
        }
    }

    fun onEvent(event: AddTimeEvent) {
        when (event) {
            is AddTimeEvent.NomeChanged -> nome = event.nome
            is AddTimeEvent.CidadeChanged -> cidade = event.cidade
            is AddTimeEvent.EstadoChanged -> estado = event.estado
            is AddTimeEvent.SaveTime -> { saveTime() }

        }
    }

    private fun saveTime() {
        viewModelScope.launch {
            if (nome.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("O nome do time não pode estar em branco"))
                return@launch
            }
            if (cidade.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("A cidade do time não pode estar em branco"))
                return@launch
            }
            if (estado.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("O estado do time não pode estar em branco"))
                return@launch
            }

            timeRepository.insertTime(
                nome = nome,
                cidade = cidade,
                estado = estado,
                id = id ?: 0
            )

            _uiEvent.send(UiEvent.NavigateBack)
        }
    }

}