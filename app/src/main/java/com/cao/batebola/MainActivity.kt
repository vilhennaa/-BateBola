package com.cao.batebola

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.cao.batebola.dados.JogadorDatabase.Companion.getInstance
import com.cao.batebola.navigation.BateBolaNavHost
import com.cao.batebola.ui.mvvm.JogadorViewModel
import com.cao.batebola.ui.theme.BateBolaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = getInstance(this)
        val viewModel = JogadorViewModel(db.jogadorDao())

        setContent {
            Box(
                modifier = Modifier
                    .safeDrawingPadding()
            ) {
                BateBolaTheme {
                    BateBolaNavHost(viewModel)
                }
            }
        }
    }
}
