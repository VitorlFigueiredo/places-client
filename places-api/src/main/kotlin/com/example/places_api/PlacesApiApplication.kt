import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class CommercialPlace(
	val type: String,
	val name: String,
	val rating: Double
)

fun main() = runBlocking {
	val client = HttpClient(CIO) {
		install(ContentNegotiation) {
			json(Json {
				ignoreUnknownKeys = true
			})
		}
	}

	try {
		// Acessa o endpoint /places no servidor
		val places: List<CommercialPlace> = client.get("http://localhost:8080/places").body()

		// Exibe os resultados
		places.forEach { println(it) }
	} finally {
		client.close()
	}
}
