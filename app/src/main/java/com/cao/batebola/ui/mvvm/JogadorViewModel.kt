package com.cao.batebola.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cao.batebola.dados.dao.JogadorDao
import com.cao.batebola.dados.entity.JogadorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class JogadorViewModel(
    private val dao : JogadorDao
) : ViewModel(){

    fun excluir(jogador: JogadorEntity) {
        viewModelScope.launch {
            dao.deleteJogador(jogador)
        }
    }

    fun gravar(jogador: JogadorEntity) {
        viewModelScope.launch {
            dao.insertJogador(jogador)
        }
    }

    suspend fun buscarAfazerPorId(id: Long): JogadorEntity? {
        return withContext(Dispatchers.IO){
            dao.getJogadorById(id)
        }
    }

    private val _jogadores = MutableStateFlow<List<JogadorEntity>>(emptyList())
    val jogadores: StateFlow<List<JogadorEntity>> get() = _jogadores

    init {
        viewModelScope.launch {
            dao.getAllJogadores().collectLatest { listaDeJogadores ->
                _jogadores.value = listaDeJogadores
            }
        }
    }

}
