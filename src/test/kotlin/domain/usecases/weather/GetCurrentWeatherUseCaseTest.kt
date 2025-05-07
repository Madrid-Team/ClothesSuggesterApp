package domain.usecases.weather

import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetCurrentWeatherUseCaseTest {
    lateinit var weatherRepository: WeatherRepository
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    lateinit var testScope: TestScope

    @BeforeEach
    fun setup() {
        weatherRepository = mockk(relaxed = true)
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)
        testScope = TestScope()

    }

    @Test
    fun `get current weather should return current weather model when latitude and longitude are valid `() {
        testScope.runTest {
            //Given
            val lat = 41.2
            val lng = 62.3
            val expectedWeatherModel = createWeatherModel()



            coEvery { weatherRepository.getWeather(lat, lng) } returns expectedWeatherModel

            //when
            val result = getCurrentWeatherUseCase.getCurrentWeather(lat, lng)

            //then
            assertEquals(expectedWeatherModel.current, result)

        }
    }

    @Test
    fun `get current weather should throw exception when lat  is invalid`() {
        testScope.runTest {
            //Given
            val lat = 112.5
            val lng = 62.3

            coEvery { weatherRepository.getWeather(lat, lng) } throws WeatherException.WeatherDataException("")

            //when & then
            assertThrows<WeatherException.WeatherDataException> {
                getCurrentWeatherUseCase.getCurrentWeather(lat, lng)
            }


        }


    }

    @Test
    fun `get current weather should throw exception when lng  is invalid`() {
        testScope.runTest {
            //Given
            val lat = 80.1
            val lng = 192.1

            coEvery { weatherRepository.getWeather(lat, lng) } throws WeatherException.WeatherDataException("")

            //when & then
            assertThrows<WeatherException.WeatherDataException> {
                getCurrentWeatherUseCase.getCurrentWeather(lat, lng)
            }


        }

    }


}