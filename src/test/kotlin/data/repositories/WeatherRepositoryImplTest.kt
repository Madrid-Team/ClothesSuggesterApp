package data.repositories

import data.remote.datasource.weather.WeatherDataSource
import domain.usecases.weather.createWeatherModel
import domain.usecases.weather.createWeatherResponseModel
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
    private lateinit var repository: WeatherRepositoryImpl

    @BeforeEach
    fun setup() {
        weatherDataSource = mockk(relaxed = true)
        repository = WeatherRepositoryImpl(
            weatherDataSource
        )
    }

    @Test
    fun `getWeather returns correct WeatherModel when datasource succeeds`() = runTest {
        // Given
        val fakeResponse = createWeatherResponseModel(
            elevation = 100.0,
            generationTime = 5.0,
            latitude = 30.0,
            longitude = 31.0,
            timezone = "Africa/Cairo",
            timezoneAbbreviation = "EET",
            utcOffsetSeconds = 7200
        )


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
}
