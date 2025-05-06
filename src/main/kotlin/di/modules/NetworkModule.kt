package di.modules

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType.Application.Json

import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                Json

            }

        }

    }


}