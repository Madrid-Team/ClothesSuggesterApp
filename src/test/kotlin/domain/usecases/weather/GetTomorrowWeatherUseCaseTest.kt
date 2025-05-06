package domain.usecases.weather

import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherExceptions
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetTomorrowWeatherUseCaseTest {    lateinit var weatherRepository: WeatherRepository
    lateinit var getTomorrowWeatherUseCase: GetTomorrowWeatherUseCase

    @BeforeEach
    fun setup(){
        weatherRepository = mockk(relaxed = true)
        getTomorrowWeatherUseCase = GetTomorrowWeatherUseCase(weatherRepository)

    }

    @Test
    fun `get tomorrow weather should return current weather model when latitude and longitude are valid `(){
        //Given
        val lat = 41.2
        val lng = 62.3
        val expectedWeatherModel = createWeatherModel()



        coEvery {  weatherRepository.getWeather(lat,lng) } returns expectedWeatherModel

        //when
        val result = getTomorrowWeatherUseCase.getTomorrowWeatherUseCase(lat,lng)

        //then
        assertEquals(expectedWeatherModel.daily,result)


    }


    @Test
    fun `get tomorrow weather should throw exception when lat  is invalid`(){
        //Given
        val lat = 112.5
        val lng = 62.3
        val expectedWeatherModel = createWeatherModel()



        coEvery {  weatherRepository.getWeather(lat,lng) } returns expectedWeatherModel

        //when & then
        assertThrows<WeatherExceptions.WeatherDataOutOfRangeException> {
            getTomorrowWeatherUseCase.getTomorrowWeatherUseCase(lat, lng)
        }





    }

    @Test
    fun `get tomorrow weather should throw exception when lng  is invalid`(){
        //Given
        val lat = 80.1
        val lng = 192.1
        val expectedWeatherModel = createWeatherModel()



        coEvery {  weatherRepository.getWeather(lat,lng) } returns expectedWeatherModel

        //when & then
        assertThrows<WeatherExceptions.WeatherDataOutOfRangeException>{
            getTomorrowWeatherUseCase.getTomorrowWeatherUseCase(lat,lng)}





    }


}