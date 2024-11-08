package com.cao.batebola.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cao.batebola.dados.dao.JogadorDao
import com.cao.batebola.dados.model.Jogador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class JogadorViewModel(
    private val dao : JogadorDao
) : ViewModel(){

    fun excluir(jogador: Jogador) {
        viewModelScope.launch {
            dao.excluirJogador(jogador)
        }
    }

    fun gravar(jogador: Jogador) {
        viewModelScope.launch {
            dao.gravarJogador(jogador)
        }
    }

    suspend fun buscarAfazerPorId(jogadorId: Int): Jogador? {
        return withContext(Dispatchers.IO){
            dao.buscarJogadorPorId(jogadorId)
        }
    }

    private val _jogadores = MutableStateFlow<List<Jogador>>(emptyList())
    val jogadores: StateFlow<List<Jogador>> get() = _jogadores

    init {
        viewModelScope.launch {
            dao.listarJogadores().collectLatest { listaDeJogadores ->
                _jogadores.value = listaDeJogadores
            }
        }
    }

}
