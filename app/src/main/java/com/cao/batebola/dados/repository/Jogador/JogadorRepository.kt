
import com.cao.batebola.dados.model.Jogador
import kotlinx.coroutines.flow.Flow


interface JogadorRepository{

    fun listarJogadores(): Flow<List<Jogador>>

    suspend fun buscarJogadorPorId(jogadorId: Int): Jogador?

    suspend fun gravarJogador(jogador: Jogador)

    suspend fun excluirJogador(jogador: Jogador)
}