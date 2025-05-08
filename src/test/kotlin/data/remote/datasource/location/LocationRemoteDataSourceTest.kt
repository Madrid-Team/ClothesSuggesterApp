package data.remote.datasource.location

import com.google.common.truth.Truth.assertThat
import data.remote.responsmodels.locationModel.IpAddressResponseModel
import data.remote.responsmodels.locationModel.LocationResponseModel
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

class LocationRemoteDataSourceTest {
    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val validIpAddress = "11.11.11.11"
    private val inValidIpAddress = "00.00"
    private lateinit var mockEngine: MockEngine
    private lateinit var httpClient: HttpClient
    private lateinit var dataSource: LocationRemoteDataSource
    private val locationResponse = createLocationRemoteModel(validIpAddress)

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

        dataSource = LocationRemoteDataSource(httpClient)
    }

    @Test
    fun `getCurrentLocation should return locationRemoteModel from server`() = runTest {

        mockResponseHandler = { request ->
            respond(
                content = jsonConfig.encodeToString(LocationResponseModel.serializer(), locationResponse),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }

        // When
        val result = dataSource.getCurrentLocation(validIpAddress)

        // Then
        assertThat(result).isEqualTo(locationResponse)
    }

    @Test
    fun `getCurrentLocation should throw TooManyRequestsException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.TooManyRequests)
        }

        // When & Then
        assertThrows<NetworkException.TooManyRequestsException> {
            dataSource.getCurrentLocation(inValidIpAddress)
        }
    }

    @Test
    fun `getCurrentLocation should throw SerializationException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respond(content = "Bad Content")
        }

        // When & Then
        assertThrows<NetworkException.SerializationException> {
            dataSource.getCurrentLocation(inValidIpAddress)
        }
    }

    @Test
    fun `getCurrentLocation should throw RequestTimeoutException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.RequestTimeout)
        }

        // When & Then
        assertThrows<NetworkException.RequestTimeoutException> {
            dataSource.getCurrentLocation(inValidIpAddress)
        }
    }


    @Test
    fun `getCurrentLocation should throw ServerErrorException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.InternalServerError)
        }

        // When & Then
        assertThrows<NetworkException.ServerErrorException> {
            dataSource.getCurrentLocation(inValidIpAddress)
        }
    }


    @Test
    fun `getIpAddress should return IpRemoteModel from server`() = runTest {
        val ipAddressBaseUrl = "https://api.ipify.org?format=json"
        val ipAddress = IpAddressResponseModel(ipAddress = ipAddressBaseUrl)

        mockResponseHandler = { request ->
            respond(
                content = jsonConfig.encodeToString(IpAddressResponseModel.serializer(), ipAddress),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }

        // When
        val result = dataSource.getIpAddress()

        // Then
        assertThat(result).isEqualTo(ipAddress)
    }

    @Test
    fun `getIpAddress should throw TooManyRequestsException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.TooManyRequests)
        }

        // When & Then
        assertThrows<NetworkException.TooManyRequestsException> {
            dataSource.getIpAddress()
        }
    }

    @Test
    fun `getIpAddress should throw SerializationException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respond(content = "Bad Content")
        }

        // When & Then
        assertThrows<NetworkException.SerializationException> {
            dataSource.getIpAddress()
        }
    }

    @Test
    fun `getIpAddress should throw RequestTimeoutException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.RequestTimeout)
        }

        // When & Then
        assertThrows<NetworkException.RequestTimeoutException> {
            dataSource.getIpAddress()
        }
    }


    @Test
    fun `getIpAddress should throw ServerErrorException when server returns server error`() = runTest {
        mockResponseHandler = { request ->
            respondError(HttpStatusCode.InternalServerError)
        }

        // When & Then
        assertThrows<NetworkException.ServerErrorException> {
            dataSource.getIpAddress()
        }
    }

}