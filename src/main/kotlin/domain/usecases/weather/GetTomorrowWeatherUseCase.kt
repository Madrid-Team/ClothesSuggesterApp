package domain.usecases.weather

import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherException

class GetTomorrowWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun getTomorrowWeather(latitude: Double, longitude: Double): DailyWeatherTemperatureModel {
        try {
            return weatherRepository.getWeather(latitude,longitude).daily
        }catch (e: WeatherException){
            throw e
        }

    }


}
