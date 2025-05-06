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
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetCurrentWeatherUseCaseTest {
    lateinit var weatherRepository: WeatherRepository
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @BeforeEach
    fun setup(){
        weatherRepository = mockk(relaxed = true)
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)

    }

    @Test
    fun `get current weather should return current weather model when latitude and longitude are valid `(){
        //Given
        val lat = 41.2
        val lng = 62.3
        val expectedWeatherModel = createWeatherModel()



        coEvery {  weatherRepository.getWeather(lat,lng) } returns expectedWeatherModel

        //when
        val result = getCurrentWeatherUseCase.getCurrentWeather(lat,lng)

        //then
        assertEquals(expectedWeatherModel.current,result)


    }


    @Test
    fun `get current weather should throw exception when lat  is invalid`(){
        //Given
        val lat = 112.5
        val lng = 62.3
        val expectedWeatherModel = createWeatherModel()



        coEvery {  weatherRepository.getWeather(lat,lng) } returns expectedWeatherModel

        //when & then
       assertThrows<WeatherExceptions.WeatherDataOutOfRangeException>{
           getCurrentWeatherUseCase.getCurrentWeather(lat,lng)}





    }

    @Test
    fun `get current weather should throw exception when lng  is invalid`(){
        //Given
        val lat = 80.1
        val lng = 192.1
        val expectedWeatherModel = createWeatherModel()



        coEvery {  weatherRepository.getWeather(lat,lng) } returns expectedWeatherModel

        //when & then
        assertThrows<WeatherExceptions.WeatherDataOutOfRangeException>{
            getCurrentWeatherUseCase.getCurrentWeather(lat,lng)}





    }


}