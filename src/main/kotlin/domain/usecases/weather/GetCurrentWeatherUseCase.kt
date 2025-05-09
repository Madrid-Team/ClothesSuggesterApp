package domain.usecases.weather

import domain.models.weatherModels.CurrentWeatherModel
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherException

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun getCurrentWeather(): CurrentWeatherModel {
        try {
            return weatherRepository.getWeather().current
        }catch (e: WeatherException){
            throw e
        }

    }

}