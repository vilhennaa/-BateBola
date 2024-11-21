package com.cao.batebola.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cao.batebola.dados.entity.Partida.Partida
import com.cao.batebola.dados.repository.Partidas.PartidaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PartidaViewModel (

    private val repository: PartidaRepository
): ViewModel(){

        fun excluir(partida: Partida){
            viewModelScope.launch {
                repository.deletarPartida(partida)
            }
        }

        fun gravar(partida: Partida){
            viewModelScope.launch {
                repository.inserirPartida(partida)
            }
        }

        private val _partidas = MutableStateFlow<List<Partida>>(emptyList())
        val partidas: StateFlow<List<Partida>> get() = _partidas

        init {
            viewModelScope.launch {
                repository.listarPartidas().collectLatest { listaDePartidas ->
                    _partidas.value = listaDePartidas
                }
            }
        }
        suspend fun getPartidaById(id:Int): Partida?{
            return repository.getPartidaById(id)
        }


}
