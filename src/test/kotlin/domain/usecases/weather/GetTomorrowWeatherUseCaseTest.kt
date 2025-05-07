package domain.usecases.weather

import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherExceptions
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetTomorrowWeatherUseCaseTest {
    lateinit var weatherRepository: WeatherRepository
    lateinit var getTomorrowWeatherUseCase: GetTomorrowWeatherUseCase
    lateinit var testScope: TestScope

    @BeforeEach
    fun setup(){
        weatherRepository = mockk(relaxed = true)
        getTomorrowWeatherUseCase = GetTomorrowWeatherUseCase(weatherRepository)
        testScope =TestScope()

    }

    @Test
    fun `get tomorrow weather should return current weather model when latitude and longitude are valid `(){
        testScope.runTest {
            //Given
            val lat = 41.2
            val lng = 62.3
            val expectedWeatherModel = createWeatherModel()
            coEvery { weatherRepository.getWeather(lat, lng) } returns expectedWeatherModel

            //when
            val result = getTomorrowWeatherUseCase.getTomorrowWeather(lat, lng)

            //then
            assertEquals(expectedWeatherModel.daily, result)

        }

    }


}