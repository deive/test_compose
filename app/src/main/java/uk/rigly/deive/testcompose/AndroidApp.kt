package uk.rigly.deive.testcompose

import android.app.Application
import androidx.room.Room
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.math.pow

/** Android App, holds native/app-wide objects. */
class AndroidApp : Application() {

    lateinit var db: DatabaseProvider
        private set

    val httpClient by lazy {
        HttpClient(CIO) {
            installRetries()
            installJsonFeature()
            install(DefaultRequest) {
                header("Accept", "application/json")
                header("Content-Type", "application/json")
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            DatabaseProvider::class.java, "database"
        ).build()
    }

}

fun HttpClientConfig<CIOEngineConfig>.installRetries() = install(HttpRequestRetry) {
    maxRetries = 3
    retryIf { _, response -> response.status == HttpStatusCode.InternalServerError }
    retryOnExceptionIf { _, cause -> cause is ServerResponseException && cause.response.status == HttpStatusCode.InternalServerError }
    delayMillis { retry -> retry.toDouble().pow(2.0).toLong() }
    modifyRequest { it.headers.append("X_RETRY_COUNT", retryCount.toString()) }
}

fun HttpClientConfig<CIOEngineConfig>.installJsonFeature() = install(ContentNegotiation) {
    json(
        Json {
            isLenient = false
            ignoreUnknownKeys = true
        }
    )
}
