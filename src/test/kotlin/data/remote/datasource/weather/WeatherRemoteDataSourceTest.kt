package data.remote.datasource.weather

import com.google.common.truth.Truth.assertThat
import data.remote.dtos.weatherDto.WeatherDto
import data.utils.NetworkException
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WeatherRemoteDataSourceTest {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val validLatitude = 41.2
    private val validLongitude = 62.3
    private val inValidLatitude = 999.9
    private val inValidLongitude = -999.9
    private val weatherResponse = createWeatherRemoteModel(latitude = validLatitude, longitude = validLongitude)
    private lateinit var mockEngine: MockEngine
    private lateinit var httpClient: HttpClient
    private lateinit var dataSource: WeatherRemoteDataSource

    private var mockResponseHandler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData = { _ ->
        respondError(HttpStatusCode.InternalServerError)
    }


    @BeforeEach
    fun setup() {
        mockEngine = MockEngine { request ->
            mockResponseHandler(request)
        }

        httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(jsonConfig)
            }
        }

        dataSource = WeatherRemoteDataSource(httpClient)
    }


    @Test
    fun `getWeather should return weatherRemoteModel from server`() = runTest {

        mockResponseHandler = { request ->
            respond(
                content = jsonConfig.encodeToString(WeatherDto.serializer(), weatherResponse),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }

        // When
        val result = dataSource.getWeather(validLatitude, validLongitude)

        // Then
        assertThat(result).isEqualTo(weatherResponse)
    }


    @Test
    fun `getWeather should throw TooManyRequestsException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.TooManyRequests)
        }

        // When & Then
        assertThrows<NetworkException.TooManyRequestsException> {
            dataSource.getWeather(inValidLatitude, inValidLongitude)
        }
    }

    @Test
    fun `getWeather should throw SerializationException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respond(content = "Bad Content")
        }

        // When & Then
        assertThrows<NetworkException.SerializationException> {
            dataSource.getWeather(inValidLatitude, inValidLongitude)
        }
    }

    @Test
    fun `getWeather should throw RequestTimeoutException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.RequestTimeout)
        }

        // When & Then
        assertThrows<NetworkException.RequestTimeoutException> {
            dataSource.getWeather(inValidLatitude, inValidLongitude)
        }
    }


    @Test
    fun `getWeather should throw ServerErrorException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.InternalServerError)
        }

        // When & Then
        assertThrows<NetworkException.ServerErrorException> {
            dataSource.getWeather(inValidLatitude, inValidLongitude)
        }
    }


}