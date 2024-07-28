import data.repository.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object Graph {

    private val httpClient by lazy {
        HttpClient {
            install(Logging)
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }

        }
    }

    val repository by lazy {
        Repository(httpClient)
    }

}