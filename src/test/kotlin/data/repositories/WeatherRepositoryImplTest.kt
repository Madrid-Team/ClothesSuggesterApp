package data.repositories

import data.remote.datasource.location.LocationDataSource
import data.remote.datasource.weather.WeatherDataSource
import data.remote.dtos.locationDto.IpAddressDto
import data.repositories.helpers.createIpResponseModel
import data.repositories.helpers.createLocationResponseModel
import domain.entities.locationEntity.IpAddress
import domain.entities.locationEntity.Location
import domain.usecases.weather.createWeatherModel
import domain.usecases.weather.createWeatherResponseModel
import domain.utils.exceptions.LocationException
import domain.utils.exceptions.WeatherException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WeatherRepositoryImplTest {
    private lateinit var weatherDataSource: WeatherDataSource
    private lateinit var locationDataSource: LocationDataSource
    private lateinit var repository: WeatherRepositoryImpl

    @BeforeEach
    fun setup() {
        weatherDataSource = mockk(relaxed = true)
        locationDataSource = mockk(relaxed = true)
        repository = WeatherRepositoryImpl(
            weatherDataSource, locationDataSource
        )
    }

    @Test
    fun `getWeather returns correct WeatherModel when datasource succeeds`() = runTest {
        // Given
        val fakeIp = createIpResponseModel("192.168.1.1")
        val fakeLocation = createLocationResponseModel(latitude = 30.0, longitude = 31.0)
        val fakeResponse = createWeatherResponseModel(
            elevation = 100.0,
            generationTime = 5.0,
            latitude = 30.0,
            longitude = 31.0,
            timezone = "Africa/Cairo",
            timezoneAbbreviation = "EET",
            utcOffsetSeconds = 7200
        )

        coEvery { locationDataSource.getIpAddress() } returns fakeIp
        coEvery { locationDataSource.getCurrentLocation("192.168.1.1") } returns fakeLocation
        coEvery { weatherDataSource.getWeather(30.0, 31.0) } returns fakeResponse

        // When
        val result = repository.getWeather()

        // Then
        val expected = createWeatherModel(
            elevation = 100.0,
            generationTime = 5.0,
            latitude = 30.0,
            longitude = 31.0,
            timezone = "Africa/Cairo",
            timezoneAbbreviation = "EET",
            utcOffsetSeconds = 7200
        )

        assertEquals(expected, result)
    }

    @Test
    fun `getWeather throws WeatherException when datasource fails`() = runTest {
        // Given
        coEvery { weatherDataSource.getWeather(any(), any()) } throws RuntimeException("API failed")

        // Then
        assertThrows<WeatherException.WeatherApiException> {
            repository.getWeather()
        }
    }

    @Test
    fun `getCurrentLocation returns correct LocationModel when datasource succeeds`() = runTest {
        // Given
        val ipAddress = createIpResponseModel("192.168.1.1")
        val fakeResponse = createLocationResponseModel(
            ip = "192.168.1.1",
            city = "Cairo",
            countryName = "Egypt",
            latitude = 30.0444,
            longitude = 31.2357,

            )
        coEvery { locationDataSource.getIpAddress() } returns ipAddress
        coEvery { locationDataSource.getCurrentLocation("192.168.1.1") } returns fakeResponse

        // When
        val result = repository.getLocation()

        // Then
        val expected = Location(
            ip = "192.168.1.1",
            city = "Cairo",
            countryName = "Egypt",
            latitude = 30.0444,
            longitude = 31.2357,

            )

        assertEquals(expected, result)
    }

    @Test
    fun `getCurrentLocation throws LocationApiException when datasource fails`() = runTest {
        coEvery { locationDataSource.getCurrentLocation(any()) } throws RuntimeException("Network error")

        assertThrows<LocationException.LocationApiException> {
            repository.getLocation()
        }
    }

    @Test
    fun `getIpAddress returns correct IpAddressModel when datasource succeeds`() = runTest {
        // Given
        val fakeResponse = IpAddressDto(ipAddress = "8.8.8.8")

        coEvery { locationDataSource.getIpAddress() } returns fakeResponse

        // When
        val result = repository.getIpAddress()

        // Then
        val expected = IpAddress(ipAddress = "8.8.8.8")

        assertEquals(expected, result)
    }

    @Test
    fun `getIpAddress throws LocationApiException when datasource fails`() = runTest {
        coEvery { locationDataSource.getIpAddress() } throws RuntimeException("API error")

        assertThrows<LocationException.LocationApiException> {
            repository.getIpAddress()
        }
    }

}
