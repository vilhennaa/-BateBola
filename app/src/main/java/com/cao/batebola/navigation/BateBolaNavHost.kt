import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cao.batebola.ui.screens.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.cao.batebola.ui.ViewModel.PartidaViewModel
import com.cao.batebola.ui.feature.addjogador.AddJogadorScreen
import com.cao.batebola.ui.feature.addtime.AddTimeScreen
import com.cao.batebola.ui.feature.seutime.SeuTimeScreen
import com.cao.batebola.ui.feature.seutime.ThirdScreen
import com.cao.batebola.ui.screens.Login_Cadastro.LoginCadastroNavHost
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

object Routes {
    const val TELA_UM = "tela_um"
    const val TELA_DOIS = "tela_dois"
    const val TELA_TRES = "tela_tres"
    const val TELA_PERFIL = "tela_perfil"
    const val ADD_JOGADOR = "add_jogador"
}

@Serializable
object SeuTimeRoute

@Serializable
data class AddJogadorRoute(val id: Long? = null)

@Serializable
data class AddTimeRoute(val id: Long? = null)

@Composable
fun BateBolaNavHost(
    viewModel: PartidaViewModel
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController, drawerState = drawerState)
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Routes.TELA_DOIS
            ) {
                composable(Routes.TELA_UM) {
                    LoginCadastroNavHost(drawerState)
                }
                composable(Routes.TELA_DOIS) {
                    ListarPartidas(navController,drawerState,viewModel)
                }
                composable(Routes.TELA_TRES) {
                    ThirdScreen(drawerState, navController)
                }

                composable<AddTimeRoute> {
                    AddTimeScreen(
                        id = it.toRoute<AddTimeRoute>()?.id,
                        navigateBack = {
                            navController.popBackStack()
                        },
                        drawerState = drawerState
                    )
                }
                composable<SeuTimeRoute> {
                    SeuTimeScreen(
                        navigateToAddJogadorScreen = { id ->
                            navController.navigate(AddJogadorRoute(id = id))
                        },
                        drawerState = drawerState
                    )
                }
                composable<AddJogadorRoute> { navBackStackEntry ->
                    val addEditRoute = navBackStackEntry.toRoute<AddJogadorRoute>()
                    AddJogadorScreen(
                        id = addEditRoute.id,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
                composable("createPartida") {
                    CreatePartidaScreen(navController, viewModel)
                }
                composable(
                    route = "editar_partida/{partidaId}",
                    arguments = listOf(navArgument("partidaId") { type = NavType.StringType })
                ) { backStackEntry ->
                    // Recupera o ID da partida passado pela rota
                    val partidaId = backStackEntry.arguments?.getString("partidaId") ?: ""
                    EditarPartidaScreen(
                        navController = navController,
                        viewModel = viewModel,
                        partidaId = partidaId.toInt(),
                        drawerState = drawerState
                    )
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {
    val coroutineScope = rememberCoroutineScope()
    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        // Botões do Drawer com navegação direta às rotas
        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(rotaAtual == Routes.TELA_DOIS)),
            onClick = {
                navController.navigate(Routes.TELA_DOIS)
                coroutineScope.launch { drawerState.close() }
            }
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(40.dp), Color.Black)
            Text(text = "Partidas cadastradas", fontSize = 30.sp, color = getColorTexto(rotaAtual == Routes.TELA_DOIS))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(rotaAtual == Routes.TELA_UM)),
            onClick = {
                navController.navigate(Routes.TELA_UM)
                coroutineScope.launch { drawerState.close() }
            }
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Login", modifier = Modifier.size(40.dp), Color.Black)
            Text(text = "Login/Cadastro", fontSize = 25.sp, color = getColorTexto(rotaAtual == Routes.TELA_UM))
        }




        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(rotaAtual == SeuTimeRoute.toString())),
            onClick = {
                navController.navigate(SeuTimeRoute)
                coroutineScope.launch { drawerState.close() }
            }
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Seu time", modifier = Modifier.size(40.dp), Color.Black)
            Text(text = "Seu time", fontSize = 30.sp, color = getColorTexto(rotaAtual == SeuTimeRoute.toString()))
        }


    }
}

// Funções auxiliares para estilização
fun getColorMenu(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) Color(0xFF064D0C) else Color.Transparent
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) Color.Black else Color.DarkGray
}
