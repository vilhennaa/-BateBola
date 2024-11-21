package com.cao.batebola

import BateBolaNavHost
import RemotePartidaRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.cao.batebola.dados.BateBolaDatabaseProvider.provide
import com.cao.batebola.dados.repository.Partidas.LocalPartidaRepository
import com.cao.batebola.ui.ViewModel.PartidaViewModel
import com.cao.batebola.ui.theme.BateBolaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isLocal = false
        val db = provide(this)

        val localPartidaRepository = LocalPartidaRepository(db.partidaDao())
        val remotePartidaRepository = RemotePartidaRepository()

        val partidaViewModel: PartidaViewModel


        if(isLocal){
            partidaViewModel = PartidaViewModel(localPartidaRepository)
        }else{
            partidaViewModel = PartidaViewModel(remotePartidaRepository)
        }

        setContent {

                BateBolaTheme {
                    BateBolaNavHost(partidaViewModel)
                }

        }
    }
}
