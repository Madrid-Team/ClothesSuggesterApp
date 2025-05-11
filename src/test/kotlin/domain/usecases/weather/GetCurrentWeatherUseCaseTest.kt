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

            val expectedWeatherModel = createWeatherModel()



            coEvery { weatherRepository.getWeather() } returns expectedWeatherModel

            //when
            val result = getCurrentWeatherUseCase.getCurrentWeather()

            //then
            assertEquals(expectedWeatherModel.current, result)

        }
    }

    @Test
    fun `getCurrentWeather should throw InvalidWeatherDataException when latitude  is invalid`() {
        testScope.runTest {
            //Given
            coEvery { weatherRepository.getWeather() } throws WeatherException.InvalidWeatherDataException("")

            //when & then
            assertThrows<WeatherException.InvalidWeatherDataException> {
                getCurrentWeatherUseCase.getCurrentWeather()
            }
        }
    }

    @Test
    fun `getCurrentWeather should throw InvalidWeatherDataException when longitude  is invalid`() {
        testScope.runTest {
            //Given
            coEvery { weatherRepository.getWeather() } throws WeatherException.InvalidWeatherDataException("")

            //when & then
            assertThrows<WeatherException.InvalidWeatherDataException> {
                getCurrentWeatherUseCase.getCurrentWeather()
            }
        }
    }

    @Test
    fun `getCurrentWeather should throw WeatherNetworkException when no internet connection`() {
        testScope.runTest {
            //Given
            coEvery { weatherRepository.getWeather() } throws WeatherException.WeatherNetworkException("")

            //when & then
            assertThrows<WeatherException.WeatherNetworkException> {
                getCurrentWeatherUseCase.getCurrentWeather()
            }
        }
    }

    @Test
    fun `getCurrentWeather should throw WeatherApiException when api failed`() {
        testScope.runTest {
            //Given
            coEvery { weatherRepository.getWeather() } throws WeatherException.WeatherApiException("")

            //when & then
            assertThrows<WeatherException.WeatherApiException> {
                getCurrentWeatherUseCase.getCurrentWeather()
            }
        }
    }
}