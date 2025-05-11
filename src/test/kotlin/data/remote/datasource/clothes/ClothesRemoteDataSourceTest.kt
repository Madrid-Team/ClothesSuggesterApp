import data.networking.constructUrl
import data.remote.datasource.clothes.ClothesRemoteDataSource
import data.remote.dtos.clothesDto.ClothesDto
import data.utils.NetworkException
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@ExperimentalCoroutinesApi
class ClothesRemoteDataSourceTest {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private lateinit var mockEngine: MockEngine
    private lateinit var httpClient: HttpClient
    private lateinit var clothesRemoteDataSource: ClothesRemoteDataSource
    private val weatherCode = "hot.json"
    private val expectedUrl = constructUrl(weatherCode)

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

         clothesRemoteDataSource = ClothesRemoteDataSource(httpClient)
    }

    @Test
    fun `getAllOutfit should return ClothesResponseModel when API call is successful`() = runTest {
        // Given
        val expectedResponse = ClothesDto(
            femaleClothes = listOf(),
            maleClothes = listOf()
        )

         mockResponseHandler = { request ->
            if (request.url.toString() == expectedUrl) {
                respond(
                    content = jsonConfig.encodeToString(ClothesDto.serializer(), expectedResponse),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                )
            } else {
                respondError(HttpStatusCode.NotFound)
            }
        }


        // When
        val result = clothesRemoteDataSource.getAllOutfit(weatherCode)

        // Then
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getAllOutfit should throw ServerErrorException when server returns server error`() = runTest {
         mockResponseHandler = { request ->
            if (request.url.toString() == expectedUrl) {
                respondError(HttpStatusCode.InternalServerError)
            } else {
                respondError(HttpStatusCode.NotFound)
            }
        }


        // When & Then
        assertThrows<NetworkException.ServerErrorException> {
            clothesRemoteDataSource.getAllOutfit(weatherCode)
        }
    }

    @Test
    fun `getAllOutfit should throw RequestTimeoutException when request times out`() = runTest {
         mockResponseHandler = { request ->
            if (request.url.toString() == expectedUrl) {
                respondError(HttpStatusCode.RequestTimeout)
            } else {
                respondError(HttpStatusCode.NotFound)
            }
        }

        // When & Then
        assertThrows<NetworkException.RequestTimeoutException> {
            clothesRemoteDataSource.getAllOutfit(weatherCode)
        }
    }

    @Test
    fun `getAllOutfit should throw TooManyRequestsException when rate limit is exceeded`() = runTest {
         mockResponseHandler = { request ->
            if (request.url.toString() == expectedUrl) {
                respondError(HttpStatusCode.TooManyRequests)
            } else {
                respondError(HttpStatusCode.NotFound)
            }
        }

        // When & Then
        assertThrows<NetworkException.TooManyRequestsException> {
            clothesRemoteDataSource.getAllOutfit(weatherCode)
        }
    }

    @Test
    fun `getAllOutfit should throw SerializationException when response is invalid JSON`() = runTest {
         mockResponseHandler = { request ->
            if (request.url.toString() == expectedUrl) {
                respond(
                    content = "{ invalid json",
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                )
            } else {
                respondError(HttpStatusCode.NotFound)
            }
        }

        // When & Then
        assertThrows<NetworkException.SerializationException> {
            clothesRemoteDataSource.getAllOutfit(weatherCode)
        }
    }
}