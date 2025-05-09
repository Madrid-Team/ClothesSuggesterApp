package domain.usecases.weather

import domain.entities.weatherEntity.CurrentWeather
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherException

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun getCurrentWeather(): CurrentWeather {
        try {
            return weatherRepository.getWeather().current
        }catch (e: WeatherException){
            throw e
        }

    }

}