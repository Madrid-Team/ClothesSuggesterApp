package domain.usecases.weather

import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.repositories.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetWeeklyWeatherUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getWeeklyWeatherUseCase: GetWeeklyWeatherUseCase
    private lateinit var testScope: TestScope

    @BeforeEach
    fun setUp() {
        weatherRepository = mockk(relaxed = true)
        getWeeklyWeatherUseCase = GetWeeklyWeatherUseCase(weatherRepository)
        testScope = TestScope()
    }

    @Test
    fun `should return daily weather from repository`() = testScope.runTest {

        val expectedDaily = DailyWeatherTemperatureModel(
            temperatureMax = listOf(13.9, 11.1, 9.8, 9.9, 8.9, 8.3, 9.9),
            temperatureMin = listOf(3.7, 4.0, 2.8, 2.2, 1.8, 0.8, 4.0),
            time = emptyList(),
            weatherCode = emptyList()
        )

        val fakeWeather = createWeatherModel(daily = expectedDaily)

        coEvery { weatherRepository.getWeather() } returns fakeWeather

        val result = getWeeklyWeatherUseCase.getWeeklyWeather()

        assertEquals(expectedDaily, result)
    }

}