package domain.usecases.weather

import domain.models.weatherModels.CurrentUnitsWeatherModel
import domain.models.weatherModels.CurrentWeatherModel
import domain.models.weatherModels.DailyUnitsWeatherTemperatureModel
import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.models.weatherModels.WeatherModel
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherExceptions
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetCurrentWeatherUseCaseTest {
    lateinit var weatherRepository: WeatherRepository
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    lateinit var testScope: TestScope

    @BeforeEach
    fun setup(){
        weatherRepository = mockk(relaxed = true)
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)
        testScope = TestScope()

    }




}