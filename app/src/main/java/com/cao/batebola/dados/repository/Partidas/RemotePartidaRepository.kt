import com.cao.batebola.dados.entity.Partida.Partida
import com.cao.batebola.dados.repository.Partidas.PartidaRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemotePartidaRepository(): PartidaRepository {

    val db = FirebaseFirestore.getInstance()
    val partidaCollection = db.collection("partida")

    override fun listarPartidas(): Flow<List<Partida>> = callbackFlow {
        val listener = partidaCollection.addSnapshotListener { dados, erro ->
            if (erro != null) {
                close(erro)
                return@addSnapshotListener
            }
            if (dados != null) {
                val partidas = dados.documents.mapNotNull { dado ->
                    dado.toObject(Partida::class.java)
                }
                trySend(partidas).isSuccess
            }
        }
        awaitClose { listener }
    }

    suspend fun getId(): Int {
        val dados = partidaCollection.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun getPartidaById(idx: Int): Partida? {
        val dados = partidaCollection.document(idx.toString()).get().await()
        return dados.toObject(Partida::class.java)
    }

    override suspend fun inserirPartida(partida: Partida) {
        val document: DocumentReference
        if (partida.id == null) {
            partida.id = getId()
            document = partidaCollection.document(partida.id.toString())
        } else {
            document = partidaCollection.document(partida.id.toString())
        }
        document.set(partida).await()
    }

    override suspend fun deletarPartida(partida: Partida) {
        partidaCollection.document(partida.id.toString()).delete().await()
    }
}
